package com.privateproperty.mapmarkets;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void showSearch(View v)
    {

        final Intent intent = new Intent(this, Markets.class);
        startActivity(intent);
    }
    public void showList (View v)
    {
        Intent intent = new Intent(this, ShoppingList.class);
        startActivity(intent);
    }
}
