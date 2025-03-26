package com.chuwa.tutorial.t02_oop.abstractclass_interface;

public interface Chinese extends People{
    public void speak();
    public void eat();
    default public void walk(){
        System.out.println("walk like a Chinese");
    };
    static String BLOG = "is Chuwa a";
}
