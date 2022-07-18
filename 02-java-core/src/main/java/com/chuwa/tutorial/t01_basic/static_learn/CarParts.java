package com.chuwa.tutorial.t01_basic.static_learn;

/**
 * @author b1go
 * @date 6/7/22 10:43 PM
 */
public class CarParts {
    // static wheel class
    public static class StaticWheel {
        public StaticWheel() {
            System.out.println("Static Wheel created");
        }

        public void drive() {
            System.out.println("drive static wheel");
        }
    }

    // non static wheel class
    public class NonStaticWheel {
        public NonStaticWheel() {
            System.out.println(" Non Static Wheel Created");
        }
    }

    // default class
    public CarParts() {
        System.out.println("Car parts Object Created!");
    }

    public static void combine() {
        System.out.println("combine car parts");
    }
}
