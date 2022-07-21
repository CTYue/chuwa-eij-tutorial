package com.chuwa.tutorial.t08_multithreading.c08_future;

import org.junit.Test;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author b1go
 * @date 7/18/22 4:48 PM
 */
public class FutureLearn {
    /**
     * Future example
     */
    @Test
    public void demo01() {
        AtomicInteger count = new AtomicInteger(0);

        ExecutorService executor = Executors.newFixedThreadPool(3);

        Callable<Integer> task1 = () -> count.getAndIncrement();
        Callable<Integer> task2 = () -> count.getAndIncrement();
        Callable<Integer> task3 = () -> count.getAndIncrement();

        Future<Integer> future1 = executor.submit(task1);
        Future<Integer> future2 = executor.submit(task2);
        Future<Integer> future3 = executor.submit(task3);

        try {
            System.out.println("result 1: " + future1.get());
            System.out.println("result 2: " + future2.get());
            System.out.println("result 3: " + future3.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * FutureTask example
     */
    @Test
    public void demo02() {
        ExecutorService threadPool = Executors.newFixedThreadPool(3);

        long startTime = System.currentTimeMillis();

        FutureTask<String> futureTask1 = new FutureTask<String>(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return Thread.currentThread() + ": task1 over";
        });

        FutureTask<String> futureTask2 = new FutureTask<String>(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return Thread.currentThread() + ": task2 over";
        });

        threadPool.submit(futureTask1);
        threadPool.submit(futureTask2);

        try {
            System.out.println(futureTask1.get());
            System.out.println(futureTask2.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        long endTime = System.currentTimeMillis();
        System.out.println("----costTime: " + (endTime - startTime) + " 毫秒");


        System.out.println(Thread.currentThread().getName() + "\t -----end");
        threadPool.shutdown();
    }
}
