package com.chuwa.tutorial.t04_design_pattern.factory.factory_method;

/**
 * @author ylyu
 * @version 1.0
 * @date 1/2/22 10:31 AM
 */
public class Client {
    public static void main(String[] args) {
        CoffeeStore store = new CoffeeStore();
        AmericanCoffeeFactory factory = new AmericanCoffeeFactory();
        store.setFactory(factory);

        Coffee coffee = store.orderCoffee();
        System.out.println(coffee.getName());
    }
}
