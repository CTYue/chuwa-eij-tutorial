package com.chuwa.tutorial.t02_oop.abstractclass_interface;

public class BeijingAthlete extends ChineseAthlete{
    @Override
    public void speak() {
        System.out.println("您好");
    }

    @Override
    public void eat() {

    }

    public void speak(String name) {
        System.out.println("您好，我叫"+name);
    }

    public void speak(String name, int age) {
        System.out.println("您好，我叫"+name+" 我今年"+age+ "岁");
    }

    public void speak(int age) {
        System.out.println("您好，我今年"+age+ "岁");
    }

    //varargs
    public void speak(String... info) {
        for(String str: info) {
            System.out.println(str);
        }
    }

//    public void speak(String[] info) {
//        for(String str: info) {
//            System.out.println(str);
//        }
//    }

//    @Override
//    protected void eat() {
//        return;
//    }
}
