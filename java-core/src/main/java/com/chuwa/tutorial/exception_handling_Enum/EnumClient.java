package com.chuwa.tutorial.exception_handling_Enum;

import org.junit.Test;

/**
 * @author b1go
 * @date 6/12/22 11:50 PM
 */
public class EnumClient {

    @Test
    public void testSeason() {
        for (Season s : Season.values()) {
            System.out.println(s);
            System.out.println(s.name());
            System.out.println(s.ordinal());
        }
    }

    @Test
    public void testSeason2() {
        for (Season2 s : Season2.values()) {
            System.out.println(s);
//            System.out.println(s.name());
//            System.out.println(s.ordinal());
            System.out.println(s.getValue());
        }
    }

}
