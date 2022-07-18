package com.chuwa.tutorial.t03_exception_handling_Enum;

/**
 * @author b1go
 * @date 5/12/22 11:49 PM
 */
public enum Season2 {
        WINTER(5),
        SPRING(10),
        SUMMER(15),
        FALL(20);

        private int value;

        private Season2(int value) {
                this.value = value;
        }

        public int getValue() {
                return value;
        }
}
