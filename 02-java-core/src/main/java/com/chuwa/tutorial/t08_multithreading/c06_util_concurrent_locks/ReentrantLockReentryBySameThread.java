package com.chuwa.tutorial.t08_multithreading.c06_util_concurrent_locks;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.locks.ReentrantLock;

class MyReentrantLock {
    public static final ReentrantLock lock = new ReentrantLock();
}

public class ReentrantLockReentryBySameThread extends Thread {

    public void run() {
        MyReentrantLock.lock.lock();//Acquire lock
        try {
            System.out.println("Entered entry area, acquired lock...");
            System.out.println("Current Thread ID:" + Thread.currentThread().getId());
            reentry();
        } finally {
            MyReentrantLock.lock.unlock();
            System.out.println("Exited entry area, released lock...");
        }
    }

    public void reentry() {
        MyReentrantLock.lock.lock();//Acquire lock again
        try{
            System.out.println("Entered reentry area, acquired lock AGAIN...");
            rereentry();
        } finally {
            MyReentrantLock.lock.unlock();
            System.out.println("Exited reentry area, released lock...");
        }
    }

    public void rereentry() {
        MyReentrantLock.lock.lock();//Acquire lock again again
        try{
            System.out.println("Entered rereentry area, acquired lock AGAIN...");
        } finally {
            MyReentrantLock.lock.unlock();
            System.out.println("Exited rereentry area, released lock...");
        }
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ReentrantLockReentryBySameThread reentry = new ReentrantLockReentryBySameThread();
        Thread[] ts = new Thread[]{new ReentrantLockReentryBySameThread(), new ReentrantLockReentryBySameThread()};
        for(Thread t: ts)
            t.start();
        for(Thread t: ts)
            t.join();
    }
}
