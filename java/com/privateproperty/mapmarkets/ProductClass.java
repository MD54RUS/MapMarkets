package com.privateproperty.mapmarkets;

/**
 * Created by Pro-rock on 24.04.2016.
 */
public class ProductClass {
    private int id;
    private String name;
    private int category;

    public ProductClass(int id, String name, int category) {
        this.id = id;
        this.name = name;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCategory() {
        return category;
    }
}
