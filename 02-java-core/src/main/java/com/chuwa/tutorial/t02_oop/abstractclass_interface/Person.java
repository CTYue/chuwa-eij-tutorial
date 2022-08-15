package com.chuwa.tutorial.t02_oop.abstractclass_interface;

/**
 * @author b1go
 * @date 5/10/22 3:09 PM
 */
public abstract class Person {
    private String name;
    private String phoneNumber;

    // abstract class have constructors
    public Person(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public Person(String name) {
        this.name = name;
    }

    public Person(){
        System.out.println("Person non-arguments construtor");
    }

    public abstract boolean signUp(); //???

    public void walk() {
        System.out.println("People Walk");
    }

    public void meeting() {
        System.out.println("People meeting");
    }
}
