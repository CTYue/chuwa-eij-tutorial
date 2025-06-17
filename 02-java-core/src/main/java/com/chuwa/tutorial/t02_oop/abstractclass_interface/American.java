package com.chuwa.tutorial.t02_oop.abstractclass_interface;

interface American extends People{
    static String str = "sss";
    public void say();
    default void speakEnglish() {
        System.out.println("Speaking English");
    }

    //Demo why default method is important
    default void whereIam() {
        Class c = this.getClass();
        System.out.println("I am in " + c.getName());
    }
}
