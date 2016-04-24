package com.privateproperty.mapmarkets;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

/**
 * Created by Pro-rock on 24.04.2016.
 */
public class MarketCategoriesLoader {
    ArrayList<ProductItemClass> products;

    public void LoadData (final Context context, final int i)
    {
        RequestQueue queue = Volley.newRequestQueue(context);

        String url ="http://vps-1097899.vpshome.pro:8000/api/store/" + i + "/categories";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response)
            {
                DBDataManager dataManager = DBDataManager.get(context);
                try {
                    products = ProductItemClass.fromJson(response);
                    for (ProductItemClass product : products)
                    {
                        if (dataManager.getProduct(product.getId()) == null) {
                            dataManager.addProduct(new ProductClass(product.getId(),product.getName(),product.getParentID()));
                        }
                        if (dataManager.getCategory(product.getParentID())== null)
                        {
                            dataManager.addCategory(product.getParentID(),product.getCategory());
                        }
                        if (dataManager.getLocation(product.getParentID(),i)==null)
                        {
                            dataManager.addLocation(i,product.getParentID(),product.getStringLocations());
                        }


                    }

                    } catch (JSONException e)
                    {
                        e.printStackTrace();
                    }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("MM", "Error Rescieve JSON Data Products");
                // TODO Auto-generated method stub

            }
        });
        queue.add(jsonArrayRequest);
    }
}
