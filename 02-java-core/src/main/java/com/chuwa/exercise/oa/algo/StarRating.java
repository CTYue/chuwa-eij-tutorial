package com.chuwa.exercise.oa.algo;

/**
 * @author b1go
 * @date 8/4/22 8:05 PM
 */
public class StarRating {

    public static void main(String[] args) {
        System.out.println(solver("5"));
        System.out.println(solver("3.8"));
        System.out.println(solver("0.7"));
        System.out.println(solver("0.38"));
        System.out.println(solver("0"));
    }

    private static String solver(String s) {
        float n = Float.parseFloat(s);
        int m = 5;
        StringBuilder sb = new StringBuilder();

        while (n >= 1) {
            sb.append("full ");
            n--;
            m--;
        }

        if (n > 0) {
            sb.append("half ");
            m--;
        }

        while (m > 0) {
            sb.append("empty ");
            m--;
        }

        return sb.toString().trim();
    }
}
