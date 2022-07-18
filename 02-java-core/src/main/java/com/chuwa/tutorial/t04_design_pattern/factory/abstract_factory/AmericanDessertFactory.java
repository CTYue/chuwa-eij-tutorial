package com.chuwa.tutorial.t04_design_pattern.factory.abstract_factory;

/**
 * @author ylyu
 * @version 1.0
 * @date 1/2/22 4:07 PM
 */
public class AmericanDessertFactory implements DessertFactory {
    @Override
    public Coffee createCoffee() {
        return new AmericanCoffee();
    }

    @Override
    public Dessert createDessert() {
        return new MatchaMousse();
    }
}
