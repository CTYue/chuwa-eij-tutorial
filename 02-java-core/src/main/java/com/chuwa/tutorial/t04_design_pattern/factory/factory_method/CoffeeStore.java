package com.chuwa.tutorial.t04_design_pattern.factory.factory_method;

/**
 * @author ylyu
 * @version 1.0
 * @date 1/2/22 9:06 AM
 */
public class CoffeeStore {

    private CoffeeFactory factory;

    public void setFactory(CoffeeFactory factory) {
        this.factory = factory;
    }

    public Coffee orderCoffee() {
        Coffee coffee = factory.createCoffee();
        coffee.addMilk();
        coffee.addSugar();

        return coffee;
    }
}
