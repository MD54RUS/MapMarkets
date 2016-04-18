package com.privateproperty.mapmarkets;

import android.graphics.Point;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Pro-rock on 29.03.2016.
 */
public class CoordinatsClass {
    private Point point;

    public CoordinatsClass(int y, int x) {
        this.point = new Point(x,y);
    }
    public static CoordinatsClass fromJson(final JSONObject object) {
        int y = object.optInt("y");
        int x = object.optInt("x");

        return new CoordinatsClass(y,x);
    }

    public static ArrayList<CoordinatsClass> fromJson(JSONArray array) {
        ArrayList<CoordinatsClass> coords = new ArrayList<>();


        for (int index = 0; index < array.length(); ++index) {
            try {
                final CoordinatsClass coord = fromJson(array.getJSONObject(index));
                if (null != coord) coords.add(coord);
            } catch (final JSONException ignored) {
                Log.e("MM", "Error Coords fromJSON array" + ignored);
            }
        }
        return coords;
    }

    public Point getPoint() {
        return point;
    }
}
