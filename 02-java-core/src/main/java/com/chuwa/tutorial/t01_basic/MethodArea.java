package com.chuwa.tutorial.t01_basic;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class MethodArea {
    public static void main(String[] args) {
        A a0 = new A();
        A a1 = new A();
        Class a0Class = a0.getClass();
        Class a1Class = a1.getClass();
        Method[] a0m = a0Class.getDeclaredMethods();
        Method[] a1m = a1Class.getDeclaredMethods();

        //Demo method area: class information (meta data), methods...
        //method area is called "metaspace" since JDK 8
        for(int i=0;i<a0m.length;i++){
            System.out.println("a0m[i].hashCode(): "+ a0m[i].hashCode()+" a1m[i].hashCode(): "+ a1m[i].hashCode());
            System.out.println(a0m[i].equals(a1m[i]));
        }

        //Demo method area and heap memory
        List objectList = new ArrayList<>();
        for(int i=0;i<5;i++) {
            B b = new B();
            objectList.add(b);
            System.out.println("b.hashCode(): "+ b.hashCode()+ " b.getClass().hashCode(): " + b.getClass().hashCode());
        }

        //Demo String constant pool
        String str = "chuwa";
        System.out.println("str.hashCode(): "+ str.hashCode());
        for(int i=0;i<5;i++){
            str = "chuwa";
            System.out.println("str.hashCode(): "+ str.hashCode());
        }
    }
}

class A {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

class B {
    String name;
    int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
