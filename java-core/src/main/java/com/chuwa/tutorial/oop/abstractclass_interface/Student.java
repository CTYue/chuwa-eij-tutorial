package com.chuwa.tutorial.oop.abstractclass_interface;

/**
 * @author b1go
 * @date 5/10/22 3:13 PM
 */
public class Student extends Person{
    public Student(String name, String phoneNumber) {
        super(name, phoneNumber);
    }

    @Override
    public boolean signUp() {
        return false;
    }

    @Override
    public void walk() {
        super.walk();
    }

    @Override
    public void meeting() {
        super.meeting();
    }
}
