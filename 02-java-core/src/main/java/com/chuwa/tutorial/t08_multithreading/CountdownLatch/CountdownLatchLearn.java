package com.chuwa.tutorial.t08_multithreading.CountdownLatch;

import java.util.concurrent.CountDownLatch;

class Worker extends Thread
{
    private final int delay;
    private final CountDownLatch latch;

    public Worker(int delay, CountDownLatch latch, String name)
    {
        super(name);
        this.delay = delay;
        this.latch = latch;
    }

    @Override
    public void run()
    {
        try
        {
            Thread.sleep(delay);
            latch.countDown();
            System.out.println(Thread.currentThread().getName()
                    + " finished");
            System.out.println();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}

public class CountdownLatchLearn {
    public static CountDownLatch latch = new CountDownLatch(4);

    public static void main(String[] args) throws InterruptedException {

        Worker first = new Worker(5,latch,
                "WORKER-1");
        Worker second = new Worker(5,latch,
                "WORKER-2");
        Worker third = new Worker(5,latch,
                "WORKER-3");
        Worker fourth = new Worker(5,latch,
                "WORKER-4");
//        first.start();
//        second.start();
//        third.start();
//        fourth.start();
        first.join();

        // The main task waits for four threads
        latch.await();
        System.out.println("finally countdown = " + latch.getCount());
        System.out.println(Thread.currentThread().getName() + " has finished");
    }
}
