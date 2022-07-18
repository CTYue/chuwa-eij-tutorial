package com.chuwa.tutorial.t04_design_pattern.factory.abstract_factory;

/**
 * @author ylyu
 * @version 1.0
 * @date 1/2/22 4:06 PM
 */
public interface DessertFactory {
    Coffee createCoffee();

    Dessert createDessert();
}
