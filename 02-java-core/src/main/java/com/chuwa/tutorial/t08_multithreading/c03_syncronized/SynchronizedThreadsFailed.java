package com.chuwa.tutorial.t08_multithreading.c03_syncronized;

/**
 * @author Yanan Lyu
 * @date 3/21/22 10:19 AM
 * @description 用object当锁，
 * 不同thread用不同锁去访问同一变量，会失效。
 */
public class SynchronizedThreadsFailed {
    public static void main(String[] args) throws InterruptedException {
        Thread[] ts = new Thread[]{
                new AddThread(),
                new DecThread(),
                new AddThread2(),
                new DecThread2()
        };
        for (Thread t : ts) {
            t.start();
        }

        for (Thread t : ts) {
            t.join();
        }

        // 永远在变， 因为加减锁的对象不一样
        System.out.println("永远在变， 因为用的不同的锁。       => " + Counter1.studentCount);
        // 永远为0，用的同一把锁。
        System.out.println("永远为0，因为用的同一把锁。         => " + Counter1.teacherCount);
    }
}

/**
 * Locker
 */
class Counter1 {
    public static final Object LOCK_STUDENT = new Object();
    public static final Object LOCK_TEACHER = new Object();
    public static final Object LOCK_COMMON = new Object();
    public static int studentCount = 0;
    public static int teacherCount = 0;
}

class AddThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            synchronized (Counter1.LOCK_STUDENT) {
                Counter1.studentCount += 1;
            }
        }
    }
}

class DecThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            synchronized (Counter1.LOCK_TEACHER) {
                Counter1.studentCount -= 1;
            }
        }
    }
}

class AddThread2 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            synchronized (Counter1.LOCK_COMMON) {
                Counter1.teacherCount += 1;
            }
        }
    }
}

class DecThread2 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            synchronized (Counter1.LOCK_COMMON) {
                Counter1.teacherCount -= 1;
            }
        }
    }
}