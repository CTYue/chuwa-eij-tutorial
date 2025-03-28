package com.chuwa.tutorial.t04_design_pattern.builder.demo_bike;

public class Shallow implements Cloneable {
    int x;
    String y;
    int[] arr;

    public Shallow(int x, int[] arr, String y) {
        this.x = x;
        this.arr = arr;
        this.y = y;
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        int[] arr = {1, 2, 3};
        Shallow obj1 = new Shallow(10, arr, "Hey");
        Shallow obj2 = (Shallow) obj1.clone();

        obj2.x = 20;
        obj2.arr[0] = 100;  // Affects both obj1 and obj2
        obj2.y = "ssss";     // Changes only obj2.y

        System.out.println(obj1.x);        // 10 primitive
        System.out.println(obj1.arr[0]);   // 100 Obj (Shared reference, so modified)
        System.out.println(obj1.y);        // "Hey" String
    }
}