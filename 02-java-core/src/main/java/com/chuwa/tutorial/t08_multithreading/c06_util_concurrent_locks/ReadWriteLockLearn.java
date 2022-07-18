package com.chuwa.tutorial.t08_multithreading.c06_util_concurrent_locks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author Yanan Lyu
 * @date 3/24/22 9:58 AM
 * @description 悲观锁。只允许一个线程写入（其他线程既不能写入也不能读取）；
 * 没有写入时，多个线程允许同时读（提高性能）。
 * 使用ReadWriteLock时，适用条件是同一个数据，有大量线程读取，但仅有少数线程修改。
 * 例如，一个论坛的帖子，回复可以看做写入操作，它是不频繁的，但是，浏览可以看做读取操作，是非常频繁的，这种情况就可以使用ReadWriteLock。
 * https://www.liaoxuefeng.com/wiki/1252599548343744/1306581002092578
 *
 * 使用ReadWriteLock可以提高读取效率：
 * ReadWriteLock只允许一个线程写入；
 * ReadWriteLock允许多个线程在没有写入时同时读取；
 * ReadWriteLock适合读多写少的场景。
 */
public class ReadWriteLockLearn {
    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();
        List<Thread> threads = new ArrayList<>();
        List<Thread> tWrites = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            Thread tRead = new Thread(() -> {
                int[] nums = counter.get();
                for (int num : nums) {
                    System.out.print(num + " ");
                }
                System.out.println();
            });
            tRead.start();
            threads.add(tRead);
        }

        for (int j = 0; j < 2; j++) {
            Thread tWrite = new Thread(() -> {
                for (int i = 0; i < 5; i++) {
                    counter.inc(i);
                }
            });
            tWrite.start();
            tWrites.add(tWrite);
        }



        Thread.sleep(15000);
        for (Thread thread : threads) {
            thread.interrupt();
        }
    }
}

class Counter {
    private final ReadWriteLock rwLock = new ReentrantReadWriteLock();
    private final Lock rLock = rwLock.readLock();
    private final Lock wLock = rwLock.writeLock();
    private final int[] counts = new int[10];

    public void inc(int index) {
        wLock.lock();
        try {
            counts[index] += 1;
        } finally {
            wLock.unlock();
        }
    }

    public int[] get() {
        rLock.lock();
        try {
            return Arrays.copyOf(counts, counts.length);
        } finally {
            rLock.unlock();
        }
    }
}
