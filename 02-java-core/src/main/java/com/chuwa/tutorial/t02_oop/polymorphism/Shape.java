package com.chuwa.tutorial.t02_oop.polymorphism;


// A sample class Shape which provides a method to get the Shape's area

public class Shape {

    public double getArea() {
        return 0;
    }

}

// A Rectangle is a Shape with a specific width and height
class Rectangle extends Shape {   // extended form the Shape class

    private double width;
    private double height;

    public Rectangle(double width, double heigh) {
        this.width = width;
        this.height = heigh;
    }

    @Override
    public double getArea() {
        return width * height;
    }

}

// A Circle is a Shape with a specific radius
class Circle extends Shape {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double getArea() {
        return 3.14 * radius * radius;
    }

}
