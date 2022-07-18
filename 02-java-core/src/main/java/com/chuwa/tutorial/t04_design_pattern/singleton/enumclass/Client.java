package com.chuwa.tutorial.t04_design_pattern.singleton.enumclass;

/**
 * @author ylyu
 * @version 1.0
 * @date 1/1/22 8:08 PM
 */
public class Client {

    public static void main(String[] args) {
        Singleton instance = Singleton.INSTANCE;
        Singleton instance1 = Singleton.INSTANCE;

        System.out.println(instance == instance1);
    }
}
