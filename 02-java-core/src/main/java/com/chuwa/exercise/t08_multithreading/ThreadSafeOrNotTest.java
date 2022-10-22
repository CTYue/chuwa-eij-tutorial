package com.chuwa.exercise.t08_multithreading;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author b1go
 * @date 10/20/22 5:13 PM
 * 1. 学会多个线程需用同一把锁才能锁成功
 * 2. 学会AtomicInteger
 */
public class ThreadSafeOrNotTest {

    @Test
    public void testGlobalCount() {
        new Thread(() -> Counter.setGlobalCount(1)).start();
        new Thread(() -> Counter.setGlobalCount(2)).start();
        new Thread(() -> Counter.setGlobalCount(3)).start();

        System.out.println(Counter.getGlobalCount());
    }

    @Test
    public void testCount() throws InterruptedException {

        Counter counter = new Counter();
        Thread t1 = new Thread(() -> System.out.println("t1: " + counter.incrementCount()));
        Thread t2 = new Thread(() -> System.out.println("t2: " + counter.decrementCount()));

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("final result: " + counter.getCount());
    }

    @Test
    public void testSyncronizedCount() throws InterruptedException {

        SyncronizedCounter counter = new SyncronizedCounter();
        Thread t1 = new Thread(() -> System.out.println("t1: " + counter.incrementCount()));
        Thread t2 = new Thread(() -> System.out.println("t2: " + counter.decrementCount()));

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("final result: " + counter.getCount());
    }

    @Test
    public void testSyncronizedCountWithTwoLocks() throws InterruptedException {

        SyncronizedCounterWithTwoLocks counter = new SyncronizedCounterWithTwoLocks();
        Thread t1 = new Thread(() -> System.out.println("t1: " + counter.incrementCount()));
        Thread t2 = new Thread(() -> System.out.println("t2: " + counter.decrementCount()));

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("final result: " + counter.getCount());
    }

    @Test
    public void testSyncronizedCountMethod() throws InterruptedException {

        SyncronizedCounterMethod counter = new SyncronizedCounterMethod();
        Thread t1 = new Thread(() -> System.out.println("t1: " + counter.incrementCount()));
        Thread t2 = new Thread(() -> System.out.println("t2: " + counter.decrementCount()));

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("final result: " + counter.getCount());
    }

    @Test
    public void testAtomicCount() throws InterruptedException {

        AtomicCounter counter = new AtomicCounter();

        Thread t1 = new Thread(() -> System.out.println("t1: " + counter.incrementCount()));
        Thread t2 = new Thread(() -> System.out.println("t2: " + counter.decrementCount()));

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("final result: " + counter.getCount());
    }
}

class Counter {
    /**
     * 下面两个变量是thread safe么？
     *
     * globalCount 不是，因为static variable 是class level的variable, 所有的instances/objects 共享。都能access和change它。
     * count 也不是，虽然每个object有自己的count，并不被多个object共享，但如果自己本身有两个方法都操作count,且该两个方法被放到不同的thread，则不safe.
     *      incrementCount(), anotherIncrementCount()
     */
    private Integer count = 0;
    private static Integer globalCount = 0;

    public static Integer getGlobalCount() {
        return globalCount;
    }

    public static void setGlobalCount(Integer globalCount) {
        Counter.globalCount = globalCount;
    }

    public int incrementCount() {
        int k = 10000;
        while (k-- > 0) {
            count++;
        }
        return count;
    }

    public int decrementCount() {
        int k = 10000;
        while (k-- > 0) {
            count--;
        }
        return count;
    }

    public Integer getCount() {
        return count;
    }
}

class SyncronizedCounter {
    /**
     * 下面两个变量是thread safe么？
     *
     * globalCount 不是，因为static variable 是class level的variable, 所有的instances/objects 共享。都能access和change它。
     * count 也不是，虽然每个object有自己的count，并不被多个object共享，但如果自己本身有两个方法都操作count,且该两个方法被放到不同的thread，则不safe.
     *      incrementCount(), anotherIncrementCount()
     */
    private Integer count = 0;

    /**
     * 注意要提供一个锁，且操作count那两个方法必须用同一把锁
     */
    final Object locker = new Object();

    public int incrementCount() {
        int k = 10000;
        while (k-- > 0) {
            // 这两个方法必须用同一个锁
            synchronized (locker) {
                count++;
            }
        }
        return count;
    }

    public int decrementCount() {
        int k = 10000;
        while (k-- > 0) {
            // 这两个方法必须用同一个锁
            synchronized (locker) {
                count--;
            }
        }
        return count;
    }

    public Integer getCount() {
        return count;
    }
}


class SyncronizedCounterWithTwoLocks {
    /**
     * 下面两个变量是thread safe么？
     *
     * globalCount 不是，因为static variable 是class level的variable, 所有的instances/objects 共享。都能access和change它。
     * count 也不是，虽然每个object有自己的count，并不被多个object共享，但如果自己本身有两个方法都操作count,且该两个方法被放到不同的thread，则不safe.
     *      incrementCount(), anotherIncrementCount()
     */
    private Integer count = 0;

    /**
     * 注意要提供2个锁，且操作count那两个方法分别用两个不同的锁
     */
    final Object locker1 = new Object();
    final Object locker2 = new Object();


    public int incrementCount() {
        int k = 10000;
        while (k-- > 0) {
            // 这两个方法必须用同一个锁
            synchronized (locker1) {
                count++;
            }
        }
        return count;
    }

    public int decrementCount() {
        int k = 10000;
        while (k-- > 0) {
            // 这两个方法必须用同一个锁
            synchronized (locker2) {
                count--;
            }
        }
        return count;
    }

    public Integer getCount() {
        return count;
    }
}

class SyncronizedCounterMethod {
    /**
     * 下面两个变量是thread safe么？
     *
     * globalCount 不是，因为static variable 是class level的variable, 所有的instances/objects 共享。都能access和change它。
     * count 也不是，虽然每个object有自己的count，并不被多个object共享，但如果自己本身有两个方法都操作count,且该两个方法被放到不同的thread，则不safe.
     *      incrementCount(), anotherIncrementCount()
     */
    private Integer count = 0;

    /**
     * 注意要提供一个锁，且操作count那两个方法必须用同一把锁
     */

    public synchronized int incrementCount() {
        int k = 10000;
        while (k-- > 0) {
            // 这两个方法必须用同一个锁
            count++;
        }
        return count;
    }

    public synchronized int decrementCount() {
        int k = 10000;
        while (k-- > 0) {
            // 这两个方法必须用同一个锁
            count--;
        }
        return count;
    }

    public Integer getCount() {
        return count;
    }
}

class AtomicCounter {
    /**
     * 下面两个变量是thread safe么？
     *
     * globalCount 不是，因为static variable 是class level的variable, 所有的instances/objects 共享。都能access和change它。
     * count 也不是，虽然每个object有自己的count，并不被多个object共享，但如果自己本身有两个方法都操作count,且该两个方法被放到不同的thread，则不safe.
     *      incrementCount(), anotherIncrementCount()
     */
    private final AtomicInteger count = new AtomicInteger(0);

    public AtomicInteger incrementCount() {
        int k = 10000;
        while (k-- > 0) {
            count.incrementAndGet();
        }
        return count;
    }

    public AtomicInteger decrementCount() {
        int k = 10000;
        while (k-- > 0) {
            count.decrementAndGet();
        }
        return count;
    }

    public AtomicInteger getCount() {
        return count;
    }
}