package com.chuwa.tutorial.t08_multithreading.c08_future;

import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

/**
 * @author b1go
 * @date 4/13/23 11:26 PM
 */
public class FutureVsCompletableFuture {

    @Test
    public void testFuture() {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        Future<Integer> futureResult = executorService.submit(() -> {
            TimeUnit.SECONDS.sleep(5); // Simulate a delay
            return 10 + 20;
        });

        try {
            // Perform other tasks while waiting for the result
            System.out.println("Performing other tasks...");

            // Get the result from the Future (blocking)
            int result = futureResult.get();
            System.out.println("Result from the future: " + result);

            System.out.println("----");
            // Prevent the main thread from exiting immediately
            for (int i = 0; i < 7; i++) {
                System.out.println("step " + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
    }

    @Test
    public void testCompletableFuture() {
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(5); // Simulate a delay
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 10 + 20;
        });

        // Perform other tasks while waiting for the result
        System.out.println("Performing other tasks...");

        // Handle the result from the CompletableFuture (non-blocking)
        completableFuture.thenAccept(result -> System.out.println("Result from the CompletableFuture: " + result));

        System.out.println("----");
        // Prevent the main thread from exiting immediately
        for (int i = 0; i < 7; i++) {
            System.out.println("step " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}

