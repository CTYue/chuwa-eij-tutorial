package com.chuwa.tutorial.t08_multithreading.c09_ForkJoin;

import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * @author b1go
 * @date 7/18/22 4:48 PM
 */
public class ForkJoinLearn {
    @Test
    public void demo01() {
        ForkJoinTask forkJoinTask = new ForkJoinTask(1, 10000);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        Future<Integer> future = forkJoinPool.submit(forkJoinTask);

        try {
            System.out.println(future.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}


class ForkJoinTask extends RecursiveTask<Integer> {
    private final int threhold = 5;
    private int first;
    private int last;

    public ForkJoinTask(int first, int last) {
        this.first = first;
        this.last = last;
    }

    @Override
    protected Integer compute() {
        int result = 0;
        if (last - first <= threhold) {
            // small task calculate the result directly
            for (int i = first; i <= last; i++) {
                result += i;
            }
        } else {
            // divide the big task into small ones
            int middle = first + (last - first) / 2;
            ForkJoinTask leftTask = new ForkJoinTask(first, middle);
            ForkJoinTask rightTask = new ForkJoinTask(middle + 1, last);

            leftTask.fork();
            rightTask.fork();
            result = leftTask.join() + rightTask.join();
        }
        return result;
    }
}
