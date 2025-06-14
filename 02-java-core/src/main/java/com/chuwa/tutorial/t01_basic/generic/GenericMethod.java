package com.chuwa.tutorial.t01_basic.generic;

import java.util.List;
import java.util.Map;

/**
 * @author b1go
 * @date 7/25/22 12:03 AM
 */
public class GenericMethod {
    public static void main(String[] args) {
        System.out.printf("Max of %d, %d and %d is %d\n\n", 3, 4, 5,
                MaximumGenericTest.maximum(Integer.valueOf(3), Integer.valueOf(4), Integer.valueOf(5)));
        System.out.printf("Max of %.1f,%.1f and %.1f is %.1f\n\n", 6.6, 8.8, 7.7,
                MaximumGenericTest.maximum(6.6, 8.8, 7.7));
        System.out.printf("Max of %s, %s and %s is %s\n", "pear", "apple", "orange",
                MaximumGenericTest.maximum("pear", "apple", "orange"));
    }
}


class MaximumGenericTest {
    /**
     * determines the largest of three Comparable objects
     */
    public static < G extends Comparable<G>> G maximum(G x, G y, G z) {
        G max = x;

        if (y.compareTo(max) > 0) {
            max = y;
        }

        if (z.compareTo(max) > 0) {
            max = z;
        }
        return max;
    }
}
