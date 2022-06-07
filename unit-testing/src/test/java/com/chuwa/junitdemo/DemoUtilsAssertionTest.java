package com.chuwa.junitdemo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

/**
 * @author b1go
 * @date 5/22/22 5:03 PM
 */
public class DemoUtilsAssertionTest {

    @Test
    public void testEqualsAndNotEquals() {
        // 1. set up
        DemoUtils demoUtils = new DemoUtils();
        int expected = 6;

        // 2. execute
        int actual = demoUtils.add(2, 4);

        // 3. assert
        Assertions.assertEquals(expected, actual);
        Assertions.assertNotEquals(7, actual);
    }

    @Disabled
    @Test
    public void testEqualsAndNotEquals_Failure() {
        // 1. set up
        DemoUtils demoUtils = new DemoUtils();
        int expected = 6;

        // 2. execute
        int actual = demoUtils.addWrong(2, 4);

        // 3. assert
//        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testAssertSame() {
        // 1. set up
        DemoUtils demoUtils = new DemoUtils();
        String str = "Ryzlink";
        Assertions.assertSame(demoUtils.getAcademy(), demoUtils.getAcademyDuplicate());
        Assertions.assertNotSame(str, demoUtils.getAcademy());
    }

    @Order(2)
    @Test
    public void testAssertTrue() {
        // 1. set up
        DemoUtils demoUtils = new DemoUtils();
        Assertions.assertTrue(demoUtils.isGreater(4,2));
        Assertions.assertFalse(demoUtils.isGreater(1,2));
    }


    @Order(1)
    @Test
    public void testArraysEquals() {
        String[] firstArray = {"A", "B", "C"};
        String[] secondArray = firstArray;

        Assertions.assertArrayEquals(firstArray, secondArray);
    }

    @Test()
    @Order(3)
    public void testException() throws Exception {
        // 1. set up
        DemoUtils demoUtils = new DemoUtils();
        Assertions.assertThrows(Exception.class, () -> demoUtils.throwException(-2));
        Assertions.assertDoesNotThrow(() -> demoUtils.throwException(5), "should not exception");
//        Assertions.assertDoesNotThrow(() -> demoUtils.throwException(-100), "should not exception");
    }
}