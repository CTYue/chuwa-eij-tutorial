package com.chuwa.tutorial.t04_design_pattern.factory.factory_method;

/**
 * @author ylyu
 * @version 1.0
 * @date 1/2/22 10:25 AM
 */
public class AmericanCoffeeFactory implements CoffeeFactory {
    @Override
    public Coffee createCoffee() {
        return new AmericanCoffee();
    }
}
