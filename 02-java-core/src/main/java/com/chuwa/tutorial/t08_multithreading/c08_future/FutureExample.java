package com.chuwa.tutorial.t08_multithreading.c08_future;

import java.util.concurrent.*;

public class FutureExample {

    public static void main(String[] args) {
        // 创建一个线程池
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        // 创建一个Callable任务
        Callable<String> task = () -> {
            // 模拟长时间运行的任务
            Thread.sleep(5000);
            System.out.println("Printing something...");
            return "Task completed";
        };

        // 提交任务并获取Future对象
        Future<String> resFuture = executorService.submit(task);

        System.out.println("Task submitted, waiting for result...");

        try {
            // 调用get()方法阻塞主线程，直到任务完成并返回结果
            String result = resFuture.get(2, TimeUnit.SECONDS);
            System.out.println("Result from task: " + result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            throw new RuntimeException(e);
        }

        // 关闭线程池
        executorService.shutdown();
    }
}
