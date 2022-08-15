package com.chuwa.tutorial.t02_oop.abstractclass_interface;

/**
 * @author b1go
 * @date 5/10/22 4:01 PM
 */
public interface InterfaceA {

    default void printSomething() {
        System.out.println("I am inside A interface");
    }
    double calculatePrice(Integer sku); //Sku = Item Number, QR-Code, Bar-Code
}

