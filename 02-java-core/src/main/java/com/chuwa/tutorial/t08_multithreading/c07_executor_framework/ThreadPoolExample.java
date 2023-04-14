package com.chuwa.tutorial.t08_multithreading.c07_executor_framework;

import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

/**
 * @author b1go
 * @date 4/13/23 11:03 PM
 */
public class ThreadPoolExample {

    @Test
    public void fixedThreadPool(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 10; i++) {
            int taskId = i + 1;
            executorService.submit(() ->
                    System.out.println("Task " + taskId + " is running on thread " + Thread.currentThread().getName()));
        }

        executorService.shutdown();
    }

    @Test
    public void cachedThreadPoolExample() {
        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0; i < 10; i++) {
            int taskId = i + 1;
            executorService.submit(() ->
                    System.out.println("Task " + taskId + " is running on thread " + Thread.currentThread().getName()));
        }

        executorService.shutdown();
    }

    @Test
    public void singleThreadExecutorExample() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        for (int i = 0; i < 10; i++) {
            int taskId = i + 1;
            executorService.submit(() ->
                    System.out.println("Task " + taskId + " is running on thread " + Thread.currentThread().getName()));
        }

        executorService.shutdown();
    }

    @Test
    public void scheduledThreadPoolExample() {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(5);

        executorService.scheduleAtFixedRate(() ->
                System.out.println("Task is running on thread " +
                        Thread.currentThread().getName()), 0, 2, TimeUnit.SECONDS);
    }

    /**
     * 使用ThreadPoolExecutor手动创建线程池可以让您更好地控制线程池的配置
     * 设置了线程池的核心和最大线程数，保持活动时间和时间单位，以及任务队列的容量。
     * 然后，我们将Lambda表达式作为任务提交给线程池来执行。
     * 最后，我们关闭线程池以确保所有任务在应用程序退出之前完成。
     *
     * 通过手动创建线程池，可以更好地控制线程池的参数，
     * 例如核心线程数、最大线程数、保持活动时间、任务队列等。
     * 这使您能够根据应用程序的需求和性能要求进行精细的调整。
     */
    @Test
    public void customThreadPoolExample() {
        int corePoolSize = 5;
        int maximumPoolSize = 5;
        long keepAliveTime = 0L;
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        int queueCapacity = 100;

        ThreadPoolExecutor executorService = new ThreadPoolExecutor(
                corePoolSize,
                maximumPoolSize,
                keepAliveTime,
                timeUnit,
                new ArrayBlockingQueue<>(queueCapacity)
        );

        for (int i = 0; i < 10; i++) {
            int taskId = i + 1;
            executorService.submit(() ->
                    System.out.println("Task " + taskId + " is running on thread " + Thread.currentThread().getName()));
        }

        executorService.shutdown();
    }

}
