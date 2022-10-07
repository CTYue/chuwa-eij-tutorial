package com.chuwa.exercise.oa.stream;

import java.util.Arrays;

/**
 * @author b1go
 * @date 10/6/22 11:48 PM
 */
public class Print1 {

    public static void main(String[] args) {
        System.out.println("System without terminal operation");

        Arrays.stream(new int[] {1,2,3}).map(i -> {
            System.out.println("doubling " + i);
            return i + 2;
        });

        System.out.println("Stream with terminal operation");
        Arrays.stream(new int[] {1,2,3}).map(i -> {
            System.out.println("doubling " + i);
            return i + 2;
        }).sum();
    }
}
