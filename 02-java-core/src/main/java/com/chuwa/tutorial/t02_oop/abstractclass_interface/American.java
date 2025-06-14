package com.chuwa.tutorial.t02_oop.abstractclass_interface;

interface American extends People{
    String str = "sss";
    public void say();
    default void speakEnglish() {
        System.out.println("Speaking English");
    }
}
