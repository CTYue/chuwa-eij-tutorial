package com.chuwa.tutorial.t04_design_pattern.builder.demo_bike;

/**
 * @author ylyu
 * @version 1.0
 * @date 1/3/22 10:27 AM
 */
public class Client {
    public static void main(String[] args) {
        //Without bike builder
        Bike rawBike = new Bike("STEEL","LEATHER","BYD","HAN");
//        Bike bike = new Bike.Builder("BYD","Qing").setFrame("SteelFrame")
//                    .setSeat("LeatherSeat").build();// "new' is not elegant when designing a builder
        //With builder pattern: builder pattern is for complex object building
        Bike bike = Bike.builder()
                .setBrand("BYD")
                .setModel("Qing")
                .setFrame("SteelFrame")
                .setSeat("LeatherSeat").build();// Elegant
        System.out.println(bike.toString());
    }
}
