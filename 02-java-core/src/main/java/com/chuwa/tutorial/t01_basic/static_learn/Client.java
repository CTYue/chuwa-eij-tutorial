package com.chuwa.tutorial.t01_basic.static_learn;

import org.junit.Test;

/**
 * @author b1go
 * @date 6/7/22 10:47 PM
 */
public class Client {
    @Test
    public void testStaticClass() {
        CarParts.StaticWheel staticWheel = new CarParts.StaticWheel();
        staticWheel.drive();
        CarParts.combine();
        CarParts.NonStaticWheel nonStaticWheel = new CarParts().new NonStaticWheel();
        nonStaticWheel.toString();
    }
}
