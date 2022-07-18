package com.chuwa.tutorial.t01_basic;


import org.junit.Assert;
import org.junit.Test;

/**
 * @author b1go
 * @date 6/7/22 8:36 PM
 */
public class StringConstantPoolTest {

    /**
     * directly initialize a string, JVM will allocate a String Constant Pool
     */
    @Test
    public void testSCPSuccess() {
        String s1 = "Cat";
        String s2 = "Cat";
        String s3 = s1;
        Assert.assertEquals(s1, s2);
        // true
        System.out.println(s1 == s2);
        // true
        System.out.println(s1 == s3);
    }

    /**
     * new a string, jvm will allocate general heap memory
     */
    @Test
    public void testSCPWithNew() {
        String s1 = new String("Cat");
        String s2 = new String("Cat");
        String s3 = s1;
        Assert.assertEquals(s1, s2);
        // false
        System.out.println(s1 == s2);
        // true
        System.out.println(s1 == s3);
    }

    /**
     * this is java doc comment
     */
    @Test
    public void testComment() {
        // print hello
        System.out.println("Hello");
        /*
        firstly print hello
        then print world
         */
        System.out.println("world");
    }
}
