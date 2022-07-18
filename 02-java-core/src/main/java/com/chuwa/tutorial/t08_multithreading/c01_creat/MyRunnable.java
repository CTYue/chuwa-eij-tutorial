package com.chuwa.tutorial.t08_multithreading.c01_creat;

/**
 * @author b1go
 * @date 3/21/22 9:04 AM
 */
public class MyRunnable implements Runnable{
    @Override
    public void run() {
        System.out.println("Start new thread using Runnable");
    }
}
