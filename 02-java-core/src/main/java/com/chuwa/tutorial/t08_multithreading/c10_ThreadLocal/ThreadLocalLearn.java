package com.chuwa.tutorial.t08_multithreading.c10_ThreadLocal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author b1go
 * @date 7/18/22 4:48 PM
 */
public class ThreadLocalLearn {

    public static void main(String[] args) {
        MyData myData = new MyData();

        ExecutorService threadPool = Executors.newFixedThreadPool(3);

        try {
            for (int i = 0; i < 3; i++) {
                threadPool.submit(() -> {
                    try {
                        for (int j = 0; j < 5; j++) {
                            Integer beforeInt = myData.threadLocalField.get();
                            myData.add();
                            Integer afterInt = myData.threadLocalField.get();
                            System.out.println(Thread.currentThread().getName() + "\t" + "beforeInt:" + beforeInt + "\t afterInt: " + afterInt);
                        }
                    } finally {
                        // The current thread's value for this thread-local variable must be removed, or it may cause
                        // memory leaks.
                        myData.threadLocalField.remove();
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }
}

/**
 * resource class
 */
class MyData {
    ThreadLocal<Integer> threadLocalField = ThreadLocal.withInitial(() -> 0);

    public void add() {
        threadLocalField.set(1 + threadLocalField.get());
    }
}