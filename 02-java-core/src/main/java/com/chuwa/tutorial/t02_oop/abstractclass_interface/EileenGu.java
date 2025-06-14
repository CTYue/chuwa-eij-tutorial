package com.chuwa.tutorial.t02_oop.abstractclass_interface;

public abstract class EileenGu implements Chinese, American{
    @Override
    public void speak() {
        System.out.println("I is what I is");
    }

    @Override
    public void eat() {

    }
}
