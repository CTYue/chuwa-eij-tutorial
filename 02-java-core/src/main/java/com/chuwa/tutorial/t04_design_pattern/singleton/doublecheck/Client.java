package com.chuwa.tutorial.t04_design_pattern.singleton.doublecheck;

/**
 * @author ylyu
 * @version 1.0
 * @date 1/1/22 7:52 PM
 */
public class Client {

    public static void main(String[] args) {
        Singleton instance = Singleton.getInstance();
        Singleton instance1 = Singleton.getInstance();

        System.out.println(instance == instance1);
    }
}
