package com.chuwa.tutorial.t08_multithreading.CountdownLatch;

import java.util.concurrent.CountDownLatch;

class Worker extends Thread
{
    private int delay;
//    private CountDownLatch latch;

//    public Worker(int delay, CountDownLatch latch, String name)
//    {
//        super(name);
//        this.delay = delay;
//        this.latch = latch;
//    }

    public Worker(int delay,String name) {
        super(name);
        this.delay = delay;
//        this.latch = latch;
    }

    @Override
    public void run()
    {
        try
        {
            Thread.sleep(delay);
//            latch.countDown();
            CountdownLatchLearn.countdown--;
            System.out.println(CountdownLatchLearn.countdown + " " + Thread.currentThread().getName()
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
    public static int countdown = Integer.valueOf(4);//without countdown latch
    public static void main(String[] args) throws InterruptedException {
//        CountDownLatch latch = new CountDownLatch(4);
        Worker first = new Worker(1000,
                "WORKER-1");
        Worker second = new Worker(2000,
                "WORKER-2");
        Worker third = new Worker(1000,
                "WORKER-3");
        Worker fourth = new Worker(1000,
                "WORKER-4");
        first.start();
//        first.join();//The calling thread wait until called thread finishes
        second.start();
//        second.join();
        third.start();
//        third.join();
        fourth.start();

        first.join();
        second.join();
        third.join();
//        fourth.join();

        // The main task waits for four threads
//        latch.await();
        System.out.println("finally countdown = " + countdown);
        // Main thread has started
        System.out.println(Thread.currentThread().getName() + " has finished");
    }
}
