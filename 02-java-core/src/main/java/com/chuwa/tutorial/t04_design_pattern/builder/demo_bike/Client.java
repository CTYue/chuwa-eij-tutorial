package com.chuwa.tutorial.t04_design_pattern.builder.demo_bike;

/**
 * @author ylyu
 * @version 1.0
 * @date 1/3/22 10:27 AM
 */
public class Client {
    public static void main(String[] args) {
//        Bike bike = new Bike.Builder("BYD","Qing").setFrame("SteelFrame")
//                    .setSeat("LeatherSeat").build();// "new' is not elegant when designing a builder
        Bike bike = Bike.builder("BYD","Han").setFrame("SteelFrame")
                .setSeat("LeatherSeat").build();// Elegant
        System.out.println(bike.toString());
    }
}
