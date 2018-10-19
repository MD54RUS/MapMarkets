package com.privateproperty.mapmarkets;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ListViewCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import java.util.List;

public class ListOfLists extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);


        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        assert fab != null;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new CustomDialogFragment().show(getSupportFragmentManager(),
                        "dialog");
            }
        });
        showList();

    }

    @Override
    protected void onResume() {
        showList();
        super.onResume();
    }

    private void showList(){
        ListViewCompat listoflist= (ListViewCompat) findViewById(R.id.ListOfList);
        DBDataManager dbDataManager = DBDataManager.get(getApplicationContext());
        final List<String> shoplist;
        shoplist = dbDataManager.getShopLists();
//        if (shoplist != null && !shoplist.isEmpty()){ for(String str :
//                shoplist)
//            Log.i("MM", "shoplist " + str);
//        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_expandable_list_item_1, shoplist);
        if (listoflist != null) {
            listoflist.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                        long arg3) {
                    Intent intent = new Intent(getApplicationContext(),ShoppingList.class);
                    intent.putExtra("LOLname",shoplist.get(arg2));
                    startActivity(intent);

                }
            });
        }


        listoflist.setAdapter(adapter);


    }
}
