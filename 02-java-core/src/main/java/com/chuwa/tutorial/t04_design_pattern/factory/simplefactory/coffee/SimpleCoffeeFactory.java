package com.chuwa.tutorial.t04_design_pattern.factory.simplefactory.coffee;

/**
 * @author ylyu
 * @version 1.0
 * @date 1/2/22 9:21 AM
 */
public class SimpleCoffeeFactory {
    public Coffee createCoffee(String type) {
        Coffee coffee;

        if ("american".equalsIgnoreCase(type)) {
            coffee = new AmericanCoffee();
        } else if ("latte".equalsIgnoreCase(type)) {
            coffee = new LatteCoffee();
        } else {
            throw new RuntimeException("no the coffee you ordered");
        }

        return coffee;
    }
}
