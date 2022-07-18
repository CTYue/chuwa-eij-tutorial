package com.chuwa.tutorial.t04_design_pattern.factory.simplefactory.coffee;

/**
 * @author ylyu
 * @version 1.0
 * @date 1/2/22 9:06 AM
 */
public class CoffeeStore {
    public Coffee orderCoffee(String type) {
        SimpleCoffeeFactory factory = new SimpleCoffeeFactory();
        Coffee coffee = factory.createCoffee(type);

        coffee.addMilk();
        coffee.addSugar();

        return coffee;
    }
}
