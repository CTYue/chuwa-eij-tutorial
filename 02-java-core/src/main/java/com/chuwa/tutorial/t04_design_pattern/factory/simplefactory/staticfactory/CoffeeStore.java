package com.chuwa.tutorial.t04_design_pattern.factory.simplefactory.staticfactory;

/**
 * @author ylyu
 * @version 1.0
 * @date 1/2/22 9:06 AM
 */
public class CoffeeStore {
    public Coffee orderCoffee(String type) {
        Coffee coffee = SimpleCoffeeFactory.createCoffee(type);
        coffee.addMilk();
        coffee.addSugar();

        return coffee;
    }
}
