package com.privateproperty.mapmarkets;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

import java.util.ArrayList;

/**
 * Created by Pro-rock on 25.03.2016.
 */
public class Markets extends AppCompatActivity{
    private ListView listView;
    ArrayList <MarketClass> markets = new ArrayList<>();
    MarketAdaptor adapter;
    //TextView txtView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.markets_layout);
        //txtView = (TextView) findViewById(R.id.txtTest);
        listView = (ListView) findViewById(R.id.listMarkets);
        final Context ctx = this;

        //txtView.setText("Response => ");
//        DataMarkets();

//    }
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://vps-1097899.vpshome.pro:8000/api/stores";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {

                markets = MarketClass.fromJson(response);
                //txtView.setText(markets.get(1).getName());
                adapter = new MarketAdaptor(ctx,markets);
                //listView = (ListView) findViewById(R.id.listMarkets);
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        Intent intent = new Intent(ctx, SearchActivity.class);
//                        int i = (int) parent.getSelectedItemId();
                        intent.putExtra("MarketID", id );
                        intent.putExtra("MarketMap",markets.get(position).getUrlMap());
                        ctx.startActivity(intent);

                    }
                });

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("MM", "Error Rescieve JSON Data Markets" + error.toString());
                // TODO Auto-generated method stub

            }
        });
        queue.add(jsonArrayRequest);



//        adapter = new MarketAdaptor(Markets.this,markets);
//
//        listView.setAdapter(adapter);




//    public void DataMarkets(){


//        markets.add(new MarketClass(0,"http://toplogos.ru/images/thumbs/preview-logo-auchan.png","urlMap","Ашан","Ardress",0,0,"City"));
       // txtView.setText("The end");
    }


}
