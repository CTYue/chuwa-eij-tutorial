package com.chuwa.tutorial.t04_design_pattern.factory.factory_method;

/**
 * @author ylyu
 * @version 1.0
 * @date 1/2/22 10:26 AM
 */
public class LatteCoffeeFactory implements CoffeeFactory {
    @Override
    public Coffee createCoffee() {
        return new LatteCoffee();
    }
}
