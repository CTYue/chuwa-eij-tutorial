package com.chuwa.tutorial.t02_oop;

import com.chuwa.tutorial.t02_oop.abstractclass_interface.*;
import org.junit.Test;

public class OopTest {
    @Test
    public void test() {
        //Anonymous class implementation
        Chinese guailing = new Chinese() {
            @Override
            public void speak() {

            }

            @Override
            public void eat() {

            }
        };// () -> {return statement}

        guailing.walk();
        System.out.println(guailing.BLOG);

    }
}
