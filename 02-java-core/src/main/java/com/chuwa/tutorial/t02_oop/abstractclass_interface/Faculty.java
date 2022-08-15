package com.chuwa.tutorial.t02_oop.abstractclass_interface;

/**
 * @author b1go
 * @date 6/10/22 3:32 PM
 */
public class Faculty extends Person {
    public Faculty(String name, String phoneNumber) {
        super(name, phoneNumber);
    }

    @Override
    public boolean signUp() {
        return false;
    }

    // show override unforced methods
    @Override
    public void walk() {
        super.walk();
    }
}
