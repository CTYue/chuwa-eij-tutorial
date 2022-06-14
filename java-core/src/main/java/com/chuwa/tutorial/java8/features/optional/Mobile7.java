package com.chuwa.tutorial.java8.features.optional;

import java.util.Optional;

/**
 * @author b1go
 * @date 5/13/22 2:41 PM
 */
public class Mobile7 {
    private long id;
    private String brand;
    private String name;
    private DisplayFeatures7 displayFeatures;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DisplayFeatures7 getDisplayFeatures() {
        return displayFeatures;
    }

    public void setDisplayFeatures(DisplayFeatures7 displayFeatures) {
        this.displayFeatures = displayFeatures;
    }
}
