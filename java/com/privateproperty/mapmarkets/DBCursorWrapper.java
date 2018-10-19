package com.privateproperty.mapmarkets;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

/**
 * Created by Pro-rock on 24.04.2016.
 */
public class DBCursorWrapper extends CursorWrapper {

    public DBCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public MarketClass getMarket(){
        int uuid = getInt(getColumnIndex(SchemaDB.MarketTable.Cols.UUID));
        String urlLogo = getString(getColumnIndex(SchemaDB.MarketTable.Cols.LOGO));
        String urlMap = getString(getColumnIndex(SchemaDB.MarketTable.Cols.MAP));
        String name = getString(getColumnIndex(SchemaDB.MarketTable.Cols.NAME));
        String address = getString(getColumnIndex(SchemaDB.MarketTable.Cols.ADDRESS));
        double lat = getDouble(getColumnIndex(SchemaDB.MarketTable.Cols.LATITUDE));
        double longitude = getDouble(getColumnIndex(SchemaDB.MarketTable.Cols.LONGITUDE));
        String city = getString(getColumnIndex(SchemaDB.MarketTable.Cols.CITY));


        return new MarketClass(uuid,urlLogo,urlMap,name,address,lat,longitude,city);
    }

    public ProductClass getProduct(){
        int uuid = getInt(getColumnIndex(SchemaDB.ProductTable.Cols.UUID));
        String name = getString(getColumnIndex(SchemaDB.ProductTable.Cols.NAME));
        int category = getInt(getColumnIndex(SchemaDB.ProductTable.Cols.CATEGORY));
        return new ProductClass(uuid,name,category);
    }
    public String getCategory(){
        return getString(getColumnIndex(SchemaDB.CategoriesTable.Cols.NAME));
    }
    public String getLocation(){
        return getString(getColumnIndex(SchemaDB.LocationTable.Cols.COORDINATES));
    }
    public String getShopList(){
        String name = getString(getColumnIndex(SchemaDB.ProductTable.Cols.NAME));
        return name;
    }
    public int getShopListId(){
        int i =  getInt(getColumnIndex(SchemaDB.ListOfLists.Cols.UUID));
        return i;
    }


}
