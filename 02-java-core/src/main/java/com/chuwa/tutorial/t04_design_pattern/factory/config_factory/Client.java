package com.chuwa.tutorial.t04_design_pattern.factory.config_factory;

/**
 * @author ylyu
 * @version 1.0
 * @date 1/2/22 4:49 PM
 */
public class Client {
    public static void main(String[] args) {
        Coffee coffee = CoffeeFactory.createCoffee("american");
        System.out.println(coffee.getName());

        System.out.println("=======================");
        Coffee coffee1 = CoffeeFactory.createCoffee("latte");
        System.out.println(coffee1.getName()
        );
    }
}
