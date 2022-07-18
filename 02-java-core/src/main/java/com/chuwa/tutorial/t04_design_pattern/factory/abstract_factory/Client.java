package com.chuwa.tutorial.t04_design_pattern.factory.abstract_factory;

/**
 * @author ylyu
 * @version 1.0
 * @date 1/2/22 4:11 PM
 */
public class Client {
    public static void main(String[] args) {
//        DessertFactory factory = new ItalyDessertFactory();
        DessertFactory factory = new AmericanDessertFactory();
        Coffee coffee = factory.createCoffee();
        Dessert dessert = factory.createDessert();

        System.out.println(coffee.getName());
        dessert.show();
    }
}
