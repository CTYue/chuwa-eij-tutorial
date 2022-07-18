package com.chuwa.tutorial.t04_design_pattern.builder.demo_bike;

/**
 * @author ylyu
 * @version 1.0
 * @date 1/3/22 10:13 AM
 */
public class OfoBuilder extends Builder {
    @Override
    public void buildFrame() {
        bike.setFrame("IRON Frame");
    }

    @Override
    public void buildSeat() {
        bike.setSeat("Yellow seat");
    }

    @Override
    public Bike createBike() {
        return bike;
    }
}
