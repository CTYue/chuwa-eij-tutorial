package com.chuwa.tutorial.t04_design_pattern.factory.simplefactory.staticfactory;

/**
 * @author ylyu
 * @version 1.0
 * @date 1/2/22 9:10 AM
 */
public class Client {
    public static void main(String[] args) {
        CoffeeStore store = new CoffeeStore();
        Coffee coffee = store.orderCoffee("latte");
        System.out.println(coffee.getName());

        System.out.println("**************************");
        Coffee american = store.orderCoffee("American");
        System.out.println(american.getName());
    }
}
