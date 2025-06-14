package com.chuwa.tutorial.t01_basic;

import org.junit.Test;

public class GradesEnumTest {
    @Test
    public void testGradesEnum() {
        System.out.println(Grades.fromScore(75));
        System.out.println(Grades.A);
    }
}
