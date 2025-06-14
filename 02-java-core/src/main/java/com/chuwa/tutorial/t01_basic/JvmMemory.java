package com.chuwa.tutorial.t01_basic;

import com.chuwa.tutorial.t00_common.pojos.Employee;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class JvmMemory {
//    public static void main(String[] args) {
//        A a0 = new A();
//        A a1 = new A();
//        Class a0Class = a0.getClass();
//        Class a1Class = a1.getClass();
//        Method[] a0m = a0Class.getDeclaredMethods();
//        Method[] a1m = a1Class.getDeclaredMethods();
//        Field[] a0f = a0Class.getDeclaredFields();
//        Field[] a1f = a1Class.getDeclaredFields();
//        //Demo method area: class information (meta data), methods...
//        //method area is called "metaspace" since JDK 8
//        System.out.println("Demo method area");
////        for(int i=0;i<a0m.length;i++){
////            System.out.println("a0m["+i+"]: "+a0m[i] + " a1m["+i+"]: "+a1m[i]);
//////            System.out.println(a0m[i].equals(a1m[i]));
//////            System.out.println(a0m[i] == a1m[i]);
////        }
//
//        for(int i=0;i<a0f.length;i++){
//            System.out.println(a0f[i] + " " + a1f[i]);
//            System.out.println("a0f[i].hashCode(): "+ a0f[i].hashCode()+" a1f[i].hashCode(): "+ a1f[i].hashCode());
//        }
//
//        //Demo method area and heap memory
//        System.out.println("Demo method area and heap memory");
//        List objectList = new ArrayList<>();
//        for(int i=0;i<5;i++) {
//            B b = new B();
//            objectList.add(b);
//            System.out.println("b.hashCode(): "+ b.hashCode()+ " b.getClass().hashCode(): " + b.getClass().hashCode());
//        }
//
//        //Demo String constant pool (a dedicated subarea in heap area)
//        System.out.println("Demo String constant pool");
//        String str = "chuwa";
//        String str2 = "dhuwa";
//        System.out.println("str.hashCode(): "+ str.hashCode());
//        System.out.println("str2.hashCode(): "+ str2.hashCode());
//
//        for(int i=0;i<5;i++){
//            str = "chuwa";
//            str2 = "dhuwa";
//            System.out.println("str.hashCode(): "+ str.hashCode());
//            System.out.println("str2.hashCode(): "+ str2.hashCode());
//        }
//
//        //Demo Stack area
//        System.out.println("simulateStack ...");
//        simulateStack(3);
//    }

    public static void main(String[] args) {
        System.out.println(Employee.str);
    }

    public static void simulateStack(int level) {
        int localLevel = level;
        int localCounter = level+1;

        System.out.println("localLevel: " + localCounter + " localCounter: " + localLevel );

        if(level > 0) simulateStack(level-1);
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
