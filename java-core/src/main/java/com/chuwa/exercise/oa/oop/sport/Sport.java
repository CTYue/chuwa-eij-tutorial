package com.chuwa.exercise.oa.oop.sport;

import java.util.Arrays;

/**
 * @author b1go
 * @date 6/16/22 12:36 AM
 */
public interface Sport {

    default void calculateAvgAge(int[] age) {
        double v = Arrays.stream(age).average().getAsDouble();
        System.out.println("The Average age of the team is " + String.format("%.2f", v));
    }

    void retirePlayer(int id);
}
