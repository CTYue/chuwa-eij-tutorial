package com.chuwa.tutorial.t02_oop;

import com.chuwa.tutorial.t02_oop.abstractclass_interface.American;
import com.chuwa.tutorial.t02_oop.abstractclass_interface.AmericanAthlete;
import com.chuwa.tutorial.t02_oop.abstractclass_interface.EileenGu;
import com.chuwa.tutorial.t02_oop.abstractclass_interface.People;
import org.junit.Test;

public class OopTest {
    @Test
    public void test() {
        People eileen = new American() {
            @Override
            public void speak() {
                System.out.println("Speak English");
            }
        };
    }
}
