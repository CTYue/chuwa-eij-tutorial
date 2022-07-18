package com.chuwa.tutorial.t08_multithreading.c02_basic_thread_handling;

/**
 * @author b1go
 * @date 5/20/22 10:42 PM
 * @description who call Thread.sleep(), who sleep.
 */
public class SleepTest {

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            System.out.println("t thread starts...");
            try {
                System.out.println("t thread starts to sleep 5 second");
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("t thread done");
        });

        System.out.println("Main thread starts...");
        t.start();
        Thread.sleep(3000);
        System.out.println("Main thread starts to sleep 3 seconds");
        System.out.println("Main thread done");
    }
}
