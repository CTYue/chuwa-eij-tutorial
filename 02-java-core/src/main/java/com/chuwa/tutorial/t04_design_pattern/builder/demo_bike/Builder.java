package com.chuwa.tutorial.t04_design_pattern.builder.demo_bike;

/**
 * @author ylyu
 * @version 1.0
 * @date 1/3/22 10:08 AM
 */
public abstract class Builder {
    protected Bike bike = new Bike();

    public abstract void buildFrame();

    public abstract void buildSeat();

    public abstract Bike createBike();
}
