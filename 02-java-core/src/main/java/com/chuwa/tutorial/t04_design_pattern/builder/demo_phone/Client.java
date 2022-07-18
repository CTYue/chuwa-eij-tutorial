package com.chuwa.tutorial.t04_design_pattern.builder.demo_phone;

/**
 * @author ylyu
 * @version 1.0
 * @date 1/3/22 11:25 AM
 */
public class Client {
    public static void main(String[] args) {
        Phone phone = new Phone.Builder()
                .cpu("intel")
                .screen("samsung")
                .memory("kingston")
                .mainBoard("asus")
                .build();

        System.out.println(phone);
    }
}
