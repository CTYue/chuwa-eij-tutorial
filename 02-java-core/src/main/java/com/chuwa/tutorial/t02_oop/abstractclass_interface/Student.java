package com.chuwa.tutorial.t02_oop.abstractclass_interface;

/**
 * @author b1go
 * @date 5/10/22 3:13 PM
 */
public class Student extends Person{
    public Student(String name, String phoneNumber) {
        super(name, phoneNumber);
    }

    public Student(){}

    @Override
    public boolean signUp() {
        return false;
    }

    @Override
    public void walk() {
        super.walk();
    }

    public void walkable() {
        super.walk();
        super.meeting();
    }

    public static void main(String[] args) {
        Student student = new Student();
        student.meeting();
    }
}
