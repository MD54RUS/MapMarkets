package com.privateproperty.mapmarkets;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Pro-rock on 25.03.2016.
 */
public class MarketClass {
    private int id;
    private String urlLogo;
    private String urlMap;
    private String name;
    private String address;
    private double latitude;
    private double longitude;
    private String city;

    public MarketClass(int id, String urlLogo, String urlMap, String name, String address, double latitude, double longitude, String city) {
        this.id = id;
        this.urlLogo = urlLogo;
        this.urlMap = urlMap;
        this.name = name;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.city = city;
    }
    public static MarketClass fromJson(final JSONObject object) {
        int id = object.optInt("id");
        String urlLogo = object.optString("urlLogo");
        String urlMap = object.optString("urlMap");
        String name  = object.optString("name");
        String address  = object.optString("address");
        double lat = object.optDouble("latitude");
        double coordinat = object.optDouble("longitude");
        String city  = object.optString("city");
        return new MarketClass(id,urlLogo,urlMap,name,address,lat,coordinat,city);
    }

    public static ArrayList<MarketClass> fromJson(JSONArray array) {
        ArrayList<MarketClass> markets = new ArrayList<>();
        Log.i("MM", "Start pars JSNARRAY");

        for (int index = 0; index < array.length(); ++index) {
            try {
                final MarketClass market = fromJson(array.getJSONObject(index));
                if (null != market) markets.add(market);
            } catch (final JSONException ignored) {
                Log.e("MM", "Error MarketClass fromJSON array");
            }
        }
        return markets;
    }


    public int getId() {
        return id;
    }

    public String getUrlLogo() {
        return urlLogo;
    }

    public String getUrlMap() {
        return urlMap;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getCity() {
        return city;
    }
}
