package com.privateproperty.mapmarkets;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Pro-rock on 29.03.2016.
 */
public class ProductItemClass {
    private int id;
    private String name;
    private int parentID;
    private String category;
    private ArrayList<ProductCategoryLocation> locations;

    public ProductItemClass(int id, String name, int parentID, ArrayList<ProductCategoryLocation> locations, String category) {
        this.id = id;
        this.name = name;
        this.parentID = parentID;
        this.locations = locations;
        this.category = category;
    }
    public static ProductItemClass fromJson(final JSONObject object, int pid, ArrayList<ProductCategoryLocation> locations, String category) {
        int id = object.optInt("id");
        String name  = object.optString("name");
        return new ProductItemClass(id,name,pid, locations, category);
    }

    public static ArrayList<ProductItemClass> fromJson(JSONArray array, int parentID, ArrayList<ProductCategoryLocation> locations, String category) {
        ArrayList<ProductItemClass> products = new ArrayList<>();
        //Log.i("MM", "Start pars JSNARRAY products");

        for (int index = 0; index < array.length(); ++index) {
            try {
                final ProductItemClass product = fromJson(array.getJSONObject(index), parentID, locations, category);
                if (null != product) products.add(product);
            } catch (final JSONException ignored) {
                Log.e("MM", "Error ProductItemClass fromJSON array");
            }
        }
        return products;
    }

    public static ArrayList<ProductItemClass> fromJson(final JSONObject object) throws JSONException {
        int id = object.optInt("id");
        String name  = object.optString("name");
        ArrayList<ProductCategoryLocation> locations = ProductCategoryLocation.fromJson(object.getJSONArray("coords"));
        //TODO использовать String для хранения координат в виде JSON объекта
//        ArrayList<ProductItemClass> products =
        return ProductItemClass.fromJson(object.getJSONArray("products"), id, locations, name);

    }

    public static ArrayList<ProductItemClass> fromJson(JSONArray array) throws JSONException {
        ArrayList<ProductItemClass> products = new ArrayList<>();
        //Log.i("MM", "Start pars JSNARRAY products");

        for (int index = 0; index < array.length(); ++index) {
            try {
                final ArrayList<ProductItemClass> product = fromJson(array.getJSONObject(index));
                for (ProductItemClass s : product) {
                    if (null != s) products.add(s);
                }


            } catch (final JSONException ignored) {
                Log.e("MM", "Error ProductItemClass fromJSON array");
            }
        }
        return products;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public ArrayList<ProductCategoryLocation> getLocations() {
        return locations;
    }
    public String getStringLocations(){
        String result = "[";
        for (ProductCategoryLocation location:
             locations) {
            for (CoordinatsClass coordinat :
                    location.getCoordinats()) {
                if (!result.equalsIgnoreCase("[")){result+=", ";}
                result +="{\"y\": "+coordinat.getPoint().y + ", \"x\": "+coordinat.getPoint().x+"}";
            }

        }
        result += "]";
        return result;
    }

    public int getParentID() {
        return parentID;
    }

    public int getId() {
        return id;
    }





//    public int getParentID() {
//        return parentID;
//    }
}
