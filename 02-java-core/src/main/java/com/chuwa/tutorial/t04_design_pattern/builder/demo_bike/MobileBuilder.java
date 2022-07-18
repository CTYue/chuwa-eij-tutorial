package com.chuwa.tutorial.t04_design_pattern.builder.demo_bike;

/**
 * @author ylyu
 * @version 1.0
 * @date 1/3/22 10:11 AM
 */
public class MobileBuilder extends Builder {
    @Override
    public void buildFrame() {
        bike.setFrame("carbon Frame");
    }

    @Override
    public void buildSeat() {
        bike.setSeat("Carbon seat");
    }

    @Override
    public Bike createBike() {
        return bike;
    }
}
