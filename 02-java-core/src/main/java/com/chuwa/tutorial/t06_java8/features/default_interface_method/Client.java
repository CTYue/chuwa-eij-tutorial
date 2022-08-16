package com.chuwa.tutorial.t06_java8.features.default_interface_method;

/**
 * @author Yanan Lyu
 * @date 5/9/22 3:58 PM
 */
public class Client {

    public static void main(String[] args) {
        DIMImpl dim = new DIMImpl();
        System.out.println("Override method: " + dim.add(1, 2));
        System.out.println("default method: " + dim.substract(1, 2));
        System.out.println("static method: " + DIML.blogName());
    }
}
