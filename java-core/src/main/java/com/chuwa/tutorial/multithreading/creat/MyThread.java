package com.chuwa.tutorial.multithreading.creat;

/**
 * @author b1go
 * @date 3/21/22 9:02 AM
 */
public class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("start new thread using extends thread");
    }
}
