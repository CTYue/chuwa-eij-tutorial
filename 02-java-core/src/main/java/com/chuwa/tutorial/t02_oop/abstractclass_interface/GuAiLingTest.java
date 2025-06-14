package com.chuwa.tutorial.t02_oop.abstractclass_interface;

import org.junit.Test;

public class GuAiLingTest {
    @Test
    public void testGuAiLing() {
        American EileenGu = new CaliforniaAthlete(){

        };
        EileenGu.speak();
        EileenGu.say();
        EileenGu.speakEnglish();
    }
}
