package com.privateproperty.mapmarkets;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pro-rock on 24.04.2016.
 */
public class DBDataManager {
        private static DBDataManager dbDataManager;
        private Context mContext;
        private SQLiteDatabase mDatabase;

        public static DBDataManager get(Context context) {
            if (dbDataManager == null) {
                dbDataManager = new DBDataManager(context);
            }
            return dbDataManager;
        }

        private DBDataManager(Context context) {
            mContext = context.getApplicationContext();
            mDatabase=new DBHelper(mContext).getWritableDatabase();
        }
        public List<MarketClass> getMarkets() {
            List<MarketClass> markets = new ArrayList<>();
            DBCursorWrapper cursor = queryMarkets(null,null);
            try {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()){
                    markets.add(cursor.getMarket());
                    cursor.moveToNext();
                }

            } finally {
                cursor.close();
            }

            return markets;
        }

        public MarketClass getMarket(int id) {
            DBCursorWrapper cursor = queryMarkets(SchemaDB.MarketTable.Cols.UUID+" = ?",new String[]{String.valueOf(id)});
            try {
                if (cursor.getCount() == 0) {return null;}
                cursor.moveToFirst();
                return cursor.getMarket();
            }

            finally {
                cursor.close();
            }
        }

        public void addMarket(MarketClass market) {
            ContentValues values = getContentValues(market);
            mDatabase.insert(SchemaDB.MarketTable.NAME,null,values);
        }

//        public void updateCrime (Crime crime) {
//            String uuidString = crime.getId().toString();
//            ContentValues values = getContentValues(crime);
//
//            mDatabase.update(CrimeDbSchema.CrimeTable.NAME,values, CrimeDbSchema.CrimeTable.Cols.UUID + " = ?", new String[] {uuidString});
//        }

//        public void deliteCrime (Crime crime) {
//            String uuidString = crime.getId().toString();
//
//            mDatabase.delete(CrimeDbSchema.CrimeTable.NAME,
//                    CrimeDbSchema.CrimeTable.Cols.UUID + " = ?", new String[] {uuidString});
//        }

        private DBCursorWrapper queryMarkets(String whereClause, String[] whereArgs) {
            Cursor cursor = mDatabase.query(SchemaDB.MarketTable.NAME,null,
                    whereClause, whereArgs, null,null,null);
            return new DBCursorWrapper(cursor);
        }

        private static ContentValues getContentValues(MarketClass market) {
            ContentValues values = new ContentValues();
            values.put(SchemaDB.MarketTable.Cols.UUID,market.getId());
            values.put(SchemaDB.MarketTable.Cols.LOGO,market.getUrlLogo());
            values.put(SchemaDB.MarketTable.Cols.MAP,market.getUrlMap());
            values.put(SchemaDB.MarketTable.Cols.NAME,market.getName());
            values.put(SchemaDB.MarketTable.Cols.ADDRESS,market.getAddress());
            values.put(SchemaDB.MarketTable.Cols.LATITUDE,market.getLatitude());
            values.put(SchemaDB.MarketTable.Cols.LONGITUDE,market.getLongitude());
            values.put(SchemaDB.MarketTable.Cols.CITY,market.getCity());

            return values;
        }
    private static ContentValues getContentValues(ProductClass product) {
        ContentValues values = new ContentValues();
        values.put(SchemaDB.ProductTable.Cols.UUID,product.getId());
        values.put(SchemaDB.ProductTable.Cols.NAME,product.getName());
        values.put(SchemaDB.ProductTable.Cols.CATEGORY,product.getCategory());


        return values;
    }
    public void addProduct(ProductClass product) {
        ContentValues values = getContentValues(product);
        mDatabase.insert(SchemaDB.ProductTable.NAME,null,values);
    }
    public void addCategory(int id, String name) {
        ContentValues values = new ContentValues();
        values.put(SchemaDB.CategoriesTable.Cols.UUID,id);
        values.put(SchemaDB.CategoriesTable.Cols.NAME,name);
        mDatabase.insert(SchemaDB.CategoriesTable.NAME,null,values);
    }
    public String getCategory(int id){
        DBCursorWrapper cursor = queryCategory(SchemaDB.MarketTable.Cols.UUID+" = ?",new String[]{String.valueOf(id)});
        try {
            if (cursor.getCount() == 0) {return null;}
            cursor.moveToFirst();
            return cursor.getCategory();
        }

        finally {
            cursor.close();
        }
    }
    public ProductClass getProduct(int id) {
        DBCursorWrapper cursor = queryProducts(SchemaDB.ProductTable.Cols.UUID+" = ?",new String[]{String.valueOf(id)});
        try {
            if (cursor.getCount() == 0) {return null;}
            cursor.moveToFirst();
            return cursor.getProduct();
        }

        finally {
            cursor.close();
        }
    }
    public String getLocation(int categoriesId, int marketId) {
        CursorWrapper cursor = queryLocation(SchemaDB.LocationTable.Cols.UUID+" = ?"+" AND "+ SchemaDB.LocationTable.Cols.MARKET+" = ?",new String[]{String.valueOf(categoriesId),String.valueOf(marketId)});
        try {
            if (cursor.getCount() == 0) {return null;}
            cursor.moveToFirst();
            return cursor.getString(cursor.getColumnIndex(SchemaDB.LocationTable.Cols.COORDINATES));
        }

        finally {
            cursor.close();
        }
    }
    public void addLocation(int market, int category, String coordinates)
    {
        ContentValues values = new ContentValues();
        values.put(SchemaDB.LocationTable.Cols.MARKET,market);
        values.put(SchemaDB.LocationTable.Cols.CATEGORY,category);
        values.put(SchemaDB.LocationTable.Cols.COORDINATES,coordinates);
        mDatabase.insert(SchemaDB.LocationTable.NAME,null,values);
    }
    private DBCursorWrapper queryProducts(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(SchemaDB.ProductTable.NAME,null,
                whereClause, whereArgs, null,null,null);
        return new DBCursorWrapper(cursor);
    }
    private DBCursorWrapper queryCategory(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(SchemaDB.CategoriesTable.NAME,null,
                whereClause, whereArgs, null,null,null);
        return new DBCursorWrapper(cursor);
    }
    private DBCursorWrapper queryLocation(String whereClause, String[] whereArgs) {
            Cursor cursor = mDatabase.query(SchemaDB.LocationTable.NAME,null,
                    whereClause, whereArgs, null,null,null);
            return new DBCursorWrapper(cursor);
        }





    }

