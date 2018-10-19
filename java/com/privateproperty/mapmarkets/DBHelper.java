package com.privateproperty.mapmarkets;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Pro-rock on 19.04.2016.
 */
public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, SchemaDB.NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + SchemaDB.MarketTable.NAME + " ("
                + SchemaDB.MarketTable.Cols.UUID + " integer primary key, "
                + SchemaDB.MarketTable.Cols.LOGO + " text, "
                + SchemaDB.MarketTable.Cols.MAP + " text, "
                + SchemaDB.MarketTable.Cols.NAME + " text, "
                + SchemaDB.MarketTable.Cols.ADDRESS + " text, "
                + SchemaDB.MarketTable.Cols.LATITUDE + " real, "
                + SchemaDB.MarketTable.Cols.LONGITUDE + " real, "
                + SchemaDB.MarketTable.Cols.CITY + " text);");

        db.execSQL("create table " + SchemaDB.CategoriesTable.NAME + " ("
                + SchemaDB.CategoriesTable.Cols.UUID + " integer primary key, "
                + SchemaDB.CategoriesTable.Cols.NAME + " text);");

        db.execSQL("create table " + SchemaDB.ProductTable.NAME + " ("
                + SchemaDB.ProductTable.Cols.UUID + " integer, "
                + SchemaDB.ProductTable.Cols.NAME + " text, "
                + SchemaDB.ProductTable.Cols.CATEGORY + " integer, "
                +"PRIMARY KEY ("+ SchemaDB.ProductTable.Cols.UUID+", "
                + SchemaDB.ProductTable.Cols.CATEGORY+")"+");");

//        db.execSQL("create table" + SchemaDB.AssortmentTable.NAME + " ("
//                + SchemaDB.AssortmentTable.Cols.UUID + "integer primary key autoincrement, "
//                + SchemaDB.AssortmentTable.Cols.MARKET + "integer, "
//                + SchemaDB.AssortmentTable.Cols.PRODUCT + "integer);");

        db.execSQL("create table " + SchemaDB.LocationTable.NAME+ " ("
                + SchemaDB.LocationTable.Cols.UUID + " integer primary key autoincrement, "
                + SchemaDB.LocationTable.Cols.MARKET + " integer, "
                + SchemaDB.LocationTable.Cols.CATEGORY + " integer, "
                + SchemaDB.LocationTable.Cols.COORDINATES + " text);");

        db.execSQL("create table " + SchemaDB.ListOfLists.NAME+ " ("
                + SchemaDB.ListOfLists.Cols.UUID + " integer primary key autoincrement, "
                + SchemaDB.ListOfLists.Cols.NAME + " text);");

        db.execSQL("create table " + SchemaDB.ListToBuy.NAME+ " ("
                + SchemaDB.ListToBuy.Cols.UUID + " integer primary key autoincrement, "
                + SchemaDB.ListToBuy.Cols.PARENTID + " integer, "
                + SchemaDB.ListToBuy.Cols.NAME + " text, "
                + SchemaDB.ListToBuy.Cols.STATUS + " integer, "
                + SchemaDB.ListToBuy.Cols.CATEGORY + " text);");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
