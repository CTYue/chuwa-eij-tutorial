package com.chuwa.tutorial.t03_exception_handling_Enum;

import org.junit.Test;

/**
 * @author b1go
 * @date 6/12/22 11:50 PM
 */
public class EnumClient {

    @Test
    public void testSeason() {
        //enumerate all elements
        for (Season season : Season.values()) {
            System.out.println(season);
//            System.out.println(season.name()); //Key
            System.out.println(season.ordinal()); //index of the enum
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
