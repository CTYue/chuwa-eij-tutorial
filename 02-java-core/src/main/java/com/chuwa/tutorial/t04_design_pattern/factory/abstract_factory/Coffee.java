package com.chuwa.tutorial.t04_design_pattern.factory.abstract_factory;

/**
 * @author ylyu
 * @version 1.0
 * @date 1/2/22 9:03 AM
 */
public abstract class Coffee {
    public abstract String getName();

    public void addSugar() {
        System.out.println("add sugar");
    }

    public void addMilk() {
        System.out.println("add milk");
    }
}
