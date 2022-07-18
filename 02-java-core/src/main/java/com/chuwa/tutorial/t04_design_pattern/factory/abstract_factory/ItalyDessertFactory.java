package com.chuwa.tutorial.t04_design_pattern.factory.abstract_factory;

/**
 * @author ylyu
 * @version 1.0
 * @date 1/2/22 4:10 PM
 */
public class ItalyDessertFactory implements DessertFactory {
    @Override
    public Coffee createCoffee() {
        return new LatteCoffee();
    }

    @Override
    public Dessert createDessert() {
        return new Tiramisu();
    }
}
