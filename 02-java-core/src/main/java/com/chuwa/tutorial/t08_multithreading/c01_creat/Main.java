package com.chuwa.tutorial.t08_multithreading.c01_creat;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author b1go
 * @date 3/21/22 9:02 AM
 * question: what's the difference between start() and run()
 */
public class Main {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        // use extends thread
        Thread t = new MyThread();
        t.start(); // -> create a new thread then run the task in the new thread.
        // t.run(); // -> run task in current process/thread
        System.out.println("***********");

        // use implements Runnable
        Thread t2 = new Thread(new MyRunnable());
        t2.start();

        // use lambda
        Thread t3 = new Thread(() -> System.out.println("Start new thread by implementing Runnable with lambda"));
        t3.start();

        // user implements Callable
        // 1. new our callable implementation class
        Callable<String> callable = new MyCallable();
        //Get ExecutorService from Executors utility class, thread pool size is 10
        ExecutorService executor = Executors.newFixedThreadPool(10);
        //create a list to hold the Future object associated with Callable
        List<Future<String>> list = new ArrayList<Future<String>>();
        Future<String> future = executor.submit(callable);
        list.add(future);
        Future<String> stringFuture = list.get(0);


        for (int i = 0; i < 5; i++) {
            Thread.sleep(1000);
            String s = future.get();
            System.out.println(s);
        }

        executor.shutdown();
    }
}
