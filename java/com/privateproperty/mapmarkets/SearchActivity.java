package com.privateproperty.mapmarkets;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

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
 * Created by Pro-rock on 25.03.2016.
 */
public class SearchActivity extends Activity {
    private EditText filterText = null;
    private EditText txtTest = null;
    ProductsAdapter adapter = null;
    ArrayList<ProductItemClass> products;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Context ctx = this;
        setContentView(R.layout.search_layout);
        final ListView listView = (ListView) findViewById(R.id.listProducts);

        filterText = (EditText) findViewById(R.id.search_box);

        filterText.addTextChangedListener(filterTextWatcher);
        int i = (int) getIntent().getLongExtra("MarketID", 0);
        final String map = getIntent().getStringExtra("MarketMap");
        ImageManager man = new ImageManager();
        man.fetchImage(ctx,604800,map,null);

//        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, PruductsMockData(i));

//        listView.setAdapter(adapter);
//    }
//    private ArrayList<ProductItemClass> PruductsMockData(int i){

        RequestQueue queue = Volley.newRequestQueue(this);

        String url ="http://vps-1097899.vpshome.pro:8000/api/store/" + i + "/categories";
        //filterText.setText(url);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {

                try {
                    products = ProductItemClass.fromJson(response);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //Log.e("MM", "End Rescieve JSON Data Products");
                adapter = new ProductsAdapter(ctx,products);
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(ctx, Map2.class);

//                        float f = parent.getSelectedItemId();

                        int[] points=new int[(adapter.getItem(id).getLocations().get(0).getCoordinats().size())*2];
//                        int p = 0;
                        for (int j=0;j<adapter.getItem(id).getLocations().get(0).getCoordinats().size();j++)
                        {
                            points[j*2] = adapter.getItem(id).getLocations().get(0).getCoordinats().get(j).getPoint().x;
                            points[j*2+1] = adapter.getItem(id).getLocations().get(0).getCoordinats().get(j).getPoint().y;
//                            p=p+2;
                        }
                        intent.putExtra("loc",points);

                        intent.putExtra("MarketMAP", map );

                        ctx.startActivity(intent);
                    }
                });




                //txtTest.setText(response.toString());

                //markets = MarketClass.fromJson(response);
                //txtView.setText(markets.get(1).getName());
                //adapter = new MarketAdaptor(ctx,markets);
                //listView = (ListView) findViewById(R.id.listMarkets);
                //listView.setAdapter(adapter);

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("MM", "Error Rescieve JSON Data Products");
                // TODO Auto-generated method stub

            }
        });
        queue.add(jsonArrayRequest);
//        String [] markets = {"Молоко","Пиво","Вода","Сок","Газировка"};
//        return products;
             }



    private TextWatcher filterTextWatcher = new TextWatcher() {

        public void afterTextChanged(Editable s) {
        }

        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {
        }

        public void onTextChanged(CharSequence s, int start, int before,
                                  int count) {
            adapter.getFilter().filter(s);
        }


    };
    class Retrievedata extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            try{
                //Your code
            }
            catch (Exception e)
            {

            }
            return null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        filterText.removeTextChangedListener(filterTextWatcher);
    }


}
