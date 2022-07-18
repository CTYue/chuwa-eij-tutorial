package com.chuwa.tutorial.t08_multithreading.c06_util_concurrent_locks;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author b1go
 * @date 3/22/22 10:03 PM
 * @description condition必须从lock中来，这样signalAll()才能通知到对应的等待中的线程
 * lockA 的condition能唤醒使用了lockA的等待中的线程。不能唤醒lockB的。
 *
 * await()会释放当前锁，进入等待状态；
 *
 * signal()会唤醒某个等待线程；
 *
 * signalAll()会唤醒所有等待线程；
 *
 * Condition可以替代wait和notify；
 * Condition对象必须从Lock对象获取。
 */
public class ConditionLearn {
    public static void main(String[] args) throws InterruptedException {
        TaskQueue q = new TaskQueue();
        List<Thread> ts = new ArrayList<>();

        // create 5 threads
        for (int i = 0; i < 5; i++) {
            // execute task
            Thread consumer = new Thread(() -> {
                while (true) {
                    try {
                        String task = q.getTask();
                        System.out.println(task + " is executed");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            System.out.println("Consumer Thread " + i + " start...");
            consumer.start();
            ts.add(consumer);
        }

        Thread producer = new Thread() {
            @Override
            public void run() {
                System.out.println("Producer Thread Run...");
                // create task
                for (int i = 0; i < 15; i++) {
                    String s = "t-" + (int) (Math.random() * 1000);
                    System.out.println("add task: " + s);
                    q.addTask(s);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        producer.start();
        producer.join();
        Thread.sleep(1000);
        for (Thread t : ts) {
            t.interrupt();
        }
    }
}

class TaskQueue extends Thread {
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();
    private final Queue<String> queue = new LinkedList<>();

    public void addTask(String s) {
        lock.lock();
        try {
            queue.add(s);
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public String getTask() throws InterruptedException {
        lock.lock();
        try {
            while (queue.isEmpty()) {
                condition.await();
            }

            return queue.remove();
        } finally {
            lock.unlock();
        }
    }
}