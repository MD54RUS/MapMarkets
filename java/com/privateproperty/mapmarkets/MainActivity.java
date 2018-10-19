package com.privateproperty.mapmarkets;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        loadMarkets();
        MarketsLoader Mloader = new MarketsLoader();
        Mloader.execute(this);
    }
    public void showSearch(View v)
    {

        Intent intent = new Intent(this, Markets.class);
        startActivity(intent);
    }
    public void showList (View v)
    {
        Intent intent = new Intent(this, ListOfLists.class);
        startActivity(intent);
    }
    public void loadMarkets() {


        String url = "http://vps-1097899.vpshome.pro:8000/api/stores";

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                ImageManager man = new ImageManager();
                ArrayList<MarketClass> markets = MarketClass.fromJson(response);
                for (MarketClass market :  markets)
                {
                    DBDataManager DataManager = DBDataManager.get(getApplicationContext());
                    MarketCategoriesLoader CLoader = new MarketCategoriesLoader();
                    CLoader.LoadData(getApplicationContext(),market.getId());
                    if (DataManager.getMarket(market.getId()) == null) {
                        DataManager.addMarket(market);
                    }
                    man.fetchImage(getApplicationContext(),604800,market.getUrlMap(),null);
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
}
