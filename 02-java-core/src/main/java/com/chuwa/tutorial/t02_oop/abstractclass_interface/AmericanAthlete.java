package com.chuwa.tutorial.t02_oop.abstractclass_interface;

public abstract class AmericanAthlete implements American{
    public void say() {
        System.out.println("I am a American athlete if I am in America.");
    }

    public void speak(){
        System.out.println("I speak English");
    }

    @Override
    public void speakEnglish(){
        System.out.println("I speak English and Spanish");
    }
}
