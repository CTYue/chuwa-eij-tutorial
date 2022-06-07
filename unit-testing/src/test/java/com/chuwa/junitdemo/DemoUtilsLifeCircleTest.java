package com.chuwa.junitdemo;

import org.junit.jupiter.api.*;


/**
 * @author b1go
 * @date 5/22/22 5:03 PM
 */
public class DemoUtilsLifeCircleTest {

    private DemoUtils demoUtils;

    @BeforeEach
    public void setupBeforeEach() {
        demoUtils = new DemoUtils();
        System.out.println("@BeforeEach executes before the execution of each test method");
    }

    private void setCommon() {
        demoUtils = new DemoUtils();
        System.out.println("@BeforeEach executes before the execution of each test method");
    }

    @AfterEach
    public void tearDownAfterEach() {
        System.out.println("Running @AfterEach");
        System.out.println();
    }

    /**
     * notice that it is static method
     * in junit 4, it is @BeforeClass
     */
    @BeforeAll
    public static void setupBeforeEachClass() {
        System.out.println("@BeforeAll executes only once before all test methods execution in the class");
    }

    @AfterAll
    public static void tearDownAfterAll() {
        System.out.println("@AfterAll executes only once after all test methods execution in the class");
    }


    @Test
    public void testEqualsAndNotEquals() {
        System.out.println("Running test: testEqualsAndNotEquals");

        Assertions.assertEquals(6, demoUtils.add(2, 4), "2+4 must be 6");
        Assertions.assertNotEquals(6, demoUtils.add(1, 9), "1+9 must not be 6");
    }

    @Test
    public void testNullAndNotNull() {

        System.out.println("Running test: testNullAndNotNull");

        String str1 = null;
        String str2 = "chuwa";

        Assertions.assertNull(demoUtils.checkNull(str1), "Object should be null");
        Assertions.assertNotNull(demoUtils.checkNull(str2), "Object should not be null");
    }
}
