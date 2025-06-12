package com.chuwa.tutorial.t02_oop.polymorphism;

/**
 * @author b1go
 * @date 6/8/22 12:01 AM
 */
public class Client {
    public static void main(String args[]) {
        Shape[] shape = new Shape[2]; // Creating shape array of size 2

        shape[0] = new Circle(2); // creating circle object at index 0
        shape[1] = new Rectangle(2, 2); // creating rectangle object at index 1

        System.out.println("Area of the Circle: " + shape[0].getArea());
        System.out.println("Area of the Rectangle: " + shape[1].getArea());
    }
    //Method signature: method identifier
    //Java method signature = method name + number of parameters + type of parameters (parameter list)
    //Overloading
    public static int add(int a, int b) {
        return a + b;
    }

    //Overloading
    public static int add(String a, int b) {
        return b;
    }
}
