package com.chuwa.tutorial.t02_oop;

import com.chuwa.tutorial.t02_oop.abstractclass_interface.*;
import org.junit.Test;

public class OopTest {
    @Test
    public void test() {
        //Anonymous class implementation
        AmericanAthlete eileen = new AmericanAthlete() {
            @Override
            public void say() {
                super.say();
            }

            @Override
            public void speak() {
                super.speak();
            }
        };

    }
}
