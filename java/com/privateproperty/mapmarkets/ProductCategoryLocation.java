package com.privateproperty.mapmarkets;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Pro-rock on 02.04.2016.
 */
public class ProductCategoryLocation {
    private ArrayList<CoordinatsClass> coordinats;

//    public static ProductCategoryLocation fromJson(final JSONObject object)  throws JSONException {
//        ArrayList<CoordinatsClass> coords = CoordinatsClass.fromJson(object.getJSONArray("coords").optJSONArray(0));
//
//        return new ProductCategoryLocation(coords);
//    }

    public static ArrayList<ProductCategoryLocation> fromJson(JSONArray array) {
        ArrayList<ProductCategoryLocation> locations = new ArrayList<>();


        for (int index = 0; index < array.length(); ++index) {
            try {
                JSONArray js = array.getJSONArray(index);
//                final ProductCategoryLocation location = fromJson(array.getJSONObject(index));
                ArrayList<CoordinatsClass> coords = CoordinatsClass.fromJson(js);
                ProductCategoryLocation location = new ProductCategoryLocation(coords);
                if (null != location) locations.add(location);
            } catch (final JSONException ignored) {
                Log.e("MM", "Error Coords fromJSON array" + ignored);
            }
        }
        return locations;
    }

    public ProductCategoryLocation(ArrayList<CoordinatsClass> coordinats) {
        this.coordinats = coordinats;
    }

    public ArrayList<CoordinatsClass> getCoordinats() {
        return coordinats;
    }
}
