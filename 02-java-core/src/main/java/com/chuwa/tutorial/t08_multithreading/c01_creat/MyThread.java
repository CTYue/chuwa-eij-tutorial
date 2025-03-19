package com.chuwa.tutorial.t08_multithreading.c01_creat;

/**
 * @author b1go
 * @date 3/21/22 9:02 AM
 */
public class MyThread extends Thread {
    @Override
    public void run() {
        try {
            Thread.sleep(5000);
            System.out.println("start new thread using extends thread");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
