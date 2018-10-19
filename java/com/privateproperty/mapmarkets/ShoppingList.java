package com.privateproperty.mapmarkets;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ListViewCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * Created by Pro-rock on 25.03.2016.
 */
public class ShoppingList extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopping_list);
        Toolbar title = (Toolbar) findViewById(R.id.shop_list_toolbar);

        title.setTitle("Список покупок: "+getIntent().getStringExtra("LOLname"));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabShops);
        assert fab != null;
        fab.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                new CustomDialogFragment().show(getSupportFragmentManager(),
                        "dialog");
            }

        });
//        showList();

    }

    @Override
    protected void onResume() {
        showList();
        super.onResume();
    }



    private void showList(){
        ListViewCompat listofShops= (ListViewCompat) findViewById(R.id.list_of_shops);
        DBDataManager dbDataManager = DBDataManager.get(getApplicationContext());
        final List<String> shoplist;
        shoplist = dbDataManager.getShopList(getIntent().getStringExtra("LOLname"));
//        if (shoplist != null && !shoplist.isEmpty()){ for(String str :
//                shoplist)
//            Log.i("MM", "shoplist " + str);
//        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_expandable_list_item_1, shoplist);
        if (listofShops != null) {
//            listofShops.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//                @Override
//                public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
//                                        long arg3) {
//                    Intent intent = new Intent(getApplicationContext(), ShoppingList.class);
//                    intent.putExtra("LOLname", shoplist.get(arg2));
//                    startActivity(intent);
//
//                }
//            });


            listofShops.setAdapter(adapter);
        }


    }
}



