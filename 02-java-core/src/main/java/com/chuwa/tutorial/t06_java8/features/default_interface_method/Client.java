package com.chuwa.tutorial.t06_java8.features.default_interface_method;

/**
 * @author Yanan Lyu
 * @date 5/9/22 3:58 PM
 */
public class Client {

    public static void main(String[] args) {
        DIML dim = new DIMImpl();
        System.out.println("Override method: " + dim.add(1, 2));
        System.out.println("default method: " + dim.substract(1, 2));
        System.out.println("static method: " + DIML.blogName());

        DIML diml2 = new DIML() {       //Anonymous class
            @Override
            public int add(int a, int b) {
                return a+b;
            }
        };


        DIML diml = (a,b) -> {return a+b;};//Lambda expression
        System.out.println("Override method: " + dim.add(1, 2));
        System.out.println("default method: " + dim.substract(1, 2));
        System.out.println("static method: " + DIML.blogName());

    }
}
