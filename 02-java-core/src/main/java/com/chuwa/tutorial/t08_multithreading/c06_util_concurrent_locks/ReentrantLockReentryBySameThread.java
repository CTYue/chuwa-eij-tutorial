package com.chuwa.tutorial.t08_multithreading.c06_util_concurrent_locks;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockReentryBySameThread {
    private final ReentrantLock lock = new ReentrantLock();

    public void entry() {
        lock.lock();
        try {
            System.out.println("Entered entry area, acquired lock...");
            reentry();
        } finally {
            lock.unlock();
            System.out.println("Exited entry area, released lock...");
        }
    }

    public void reentry() {
        lock.lock();
        try{
            System.out.println("Entered reentry area, acquired lock AGAIN...");
            rereentry();
        } finally {
            lock.unlock();
            System.out.println("Exited reentry area, released lock...");
        }
    }

    public void rereentry() {
        lock.lock();
        try{
            System.out.println("Entered rereentry area, acquired lock AGAIN...");
        } finally {
            lock.unlock();
            System.out.println("Exited rereentry area, released lock...");
        }
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ReentrantLockReentryBySameThread reentry = new ReentrantLockReentryBySameThread();
        Thread t = new Thread(reentry::entry);
        t.start();
        t.join();
    }
}
