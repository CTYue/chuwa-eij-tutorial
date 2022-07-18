package com.chuwa.tutorial.t04_design_pattern.builder.demo_bike;

/**
 * @author ylyu
 * @version 1.0
 * @date 1/3/22 10:19 AM
 */
public class Director {
    private final Builder builder;

    public Director(Builder builder) {
        this.builder = builder;
    }

    public Bike construct() {
        builder.buildFrame();
        builder.buildSeat();

        return builder.createBike();
    }
}
