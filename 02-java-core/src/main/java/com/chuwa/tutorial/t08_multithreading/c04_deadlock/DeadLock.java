package com.chuwa.tutorial.t08_multithreading.c04_deadlock;

/**
 * 如果没有deadlock，while(true)里的数值会一直变下去。
 *
 * 死锁发生后，没有任何机制能解除死锁，只能强制结束JVM进程。
 *
 * 因此，在编写多线程应用时，要特别注意防止死锁。因为死锁一旦形成，就只能强制结束进程。
 *
 * 那么我们应该如何避免死锁呢？答案是：线程获取锁的顺序要一致。即严格按照先获取lockA，再获取lockB的顺序
 * @author bigo
 */
public class DeadLock {
    public static void main(String[] args) throws InterruptedException {
        Thread[] ts = new Thread[] {
                new Thread(() -> {
                    while(true) {
                        Counter.add(2);
                    }
                }),
                new Thread(() -> {
                    while(true) {
                        Counter.dec(2);
                    }
                }),
        };
        for (Thread t : ts) {
            t.start();
        }

        for (Thread t : ts) {
            t.join();
        }

        System.out.println("main end");
    }
}

/**
 * Locker
 */
class Counter {
    public static final Object LOCK_A = new Object();
    public static final Object LOCK_B = new Object();
    private static int value = 0;
    private static int another = 0;

    public static void add (int m) {
        synchronized (LOCK_A) {
            value += m; // A
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            synchronized (LOCK_B) {
                another += m;
            }
        }

        System.out.println("value is: " + value + ", another is: " + another);
    }

    public static void dec(int m) {
        synchronized (LOCK_B) {
            another -= m; //B
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            synchronized (LOCK_A) {
                value -= m;
            }
        }

        System.out.println("value is: " + value + ", another is: " + another);
    }
}


