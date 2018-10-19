package com.privateproperty.mapmarkets;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

import java.util.ArrayList;

/**
 * Created by Pro-rock on 24.04.2016.
 */
public class MarketsLoader extends AsyncTask{
    public void LoadData(final Context context) {

        String url = "http://vps-1097899.vpshome.pro:8000/api/stores";

        RequestQueue queue = Volley.newRequestQueue(context);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                ImageManager man = new ImageManager();
                ArrayList<MarketClass> markets = MarketClass.fromJson(response);
                for (MarketClass market :  markets)
                {
                    DBDataManager DataManager = DBDataManager.get(context);
                    MarketCategoriesLoader CLoader = new MarketCategoriesLoader();
                    CLoader.LoadData(context,market.getId());
                    if (DataManager.getMarket(market.getId()) == null) {
                        DataManager.addMarket(market);
                    }
                    man.fetchImage(context,604800,market.getUrlMap(),null);
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("MM", "Error Rescieve JSON Data Markets" + error.toString());
                // TODO Auto-generated method stub

            }
        });
        queue.add(jsonArrayRequest);
    }

    @Override
    protected Object doInBackground(Object[] params) {
        final Context context = (Context) params[0];
        String url = "http://vps-1097899.vpshome.pro:8000/api/stores";

        RequestQueue queue = Volley.newRequestQueue(context);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                ImageManager man = new ImageManager();
                ArrayList<MarketClass> markets = MarketClass.fromJson(response);
                for (MarketClass market :  markets)
                {
                    DBDataManager DataManager = DBDataManager.get(context);
                    MarketCategoriesLoader CLoader = new MarketCategoriesLoader();
                    CLoader.LoadData(context,market.getId());
                    if (DataManager.getMarket(market.getId()) == null) {
                        DataManager.addMarket(market);
                    }
                    man.fetchImage(context,604800,market.getUrlMap(),null);
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("MM", "Error Rescieve JSON Data Markets" + error.toString());
                // TODO Auto-generated method stub

            }
        });
        queue.add(jsonArrayRequest);
        return null;
    }
}