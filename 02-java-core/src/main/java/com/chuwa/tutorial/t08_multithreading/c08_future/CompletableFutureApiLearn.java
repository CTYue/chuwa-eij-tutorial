package com.chuwa.tutorial.t08_multithreading.c08_future;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertTrue;

/**
 * @author b1go
 * @date 7/18/22 4:48 PM
 */
public class CompletableFutureApiLearn {

    /**
     * Using CompletableFuture as a Simple Future.
     */
    @Test
    public void demo01() {
        CompletableFuture<String> completableFuture = new CompletableFuture();

        ExecutorService executorService = Executors.newCachedThreadPool();

        executorService.submit(() -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            completableFuture.complete("Hello");
        });

        try {
            System.out.println(completableFuture.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Using Static methods runAsync and supplyAsync to create a CompletableFuture
     * instance out of Runnable and Supplier functional types correspondingly.
     *
     * CompletableFuture.supplyAsync 有返回值 - CompletableFuture<String>
     * CompletableFuture.runAsync（）没返回值 - CompletableFuture<Void>。参数是runnable 对象。
     */
    @Test
    public void demo02() {

        /**
         * CompletableFuture.supplyAsync() 方法返回一个 CompletableFuture<U> 对象，其中的 U 类型是 Supplier 对象的返回类型；
         * 而 CompletableFuture.runAsync() 方法返回一个 CompletableFuture<Void> 对象，表示异步任务没有返回值。
         */
        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread() + ": Hello1");
            return "Hello1";
        });
        CompletableFuture<Void> cf2 = CompletableFuture.runAsync(() ->
                System.out.println(Thread.currentThread() + ": Hello2"));

        try {
            System.out.println(cf2.isDone());
            Thread.sleep(1000);
            System.out.println(cf2.isDone());

            System.out.println(cf1.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Using thenApply Method process the result, and returns a Future that holds a
     * value returned by a function:
     */
    @Test
    public void demo03() {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> "Hello");
        CompletableFuture<String> future = completableFuture
                .thenApply(s -> s + " World");

        try {
            System.out.println(future.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * The thenAccept method receives a Consumer(no return) and passes it the result of the computation
     */
    @Test
    public void demo04() {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> "Hello");

        CompletableFuture<Void> future = completableFuture
                .thenAccept(s -> System.out.println("Computation returned: " + s));

        try {
            future.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * If we neither need the value of the computation, nor want to return some value at the
     * end of the chain, then we can pass a Runnable lambda to the thenRun method
     */
    @Test
    public void demo05() {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> "Hello");

        CompletableFuture<Void> future = completableFuture
                .thenRun(() -> System.out.println("Computation finished."));

        try {
            future.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Using the thenCompose method to chain two Futures sequentially.
     * The thenApply and the thenCompose methods closely relate to the map and flatMap methods of Stream
     */
    @Test
    public void demo06() {
        CompletableFuture<String> future1
                = CompletableFuture.supplyAsync(() -> "Hello")
                .thenCompose(s -> CompletableFuture.supplyAsync(() -> s + " World"));

        CompletableFuture<CompletableFuture<String>> future2 = CompletableFuture.supplyAsync(() -> "Hello")
                .thenApply(s -> CompletableFuture.supplyAsync(() -> s + " World"));

        try {
            System.out.println(future1.get());

            System.out.println(future2.get());
            System.out.println(future2.get().get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * Using the thenCombine method to execute two independent Futures and do something
     * with their results and return the final result
     */
    @Test
    public void demo07() {
        CompletableFuture<String> completableFuture
                = CompletableFuture.supplyAsync(() -> {
                    System.out.println("1");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println("2");
                    return "Hello";
                })
                .thenCombine(CompletableFuture.supplyAsync(() -> {
                    System.out.println("3");
                    return " World";
                }), (s1, s2) -> s1 + s2);

        try {
            System.out.println(completableFuture.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * Using the thenAcceptBoth to execute two independent Futures and do something
     * with their results, but don't pass any result
     */
    @Test
    public void demo08() {
        CompletableFuture future = CompletableFuture.supplyAsync(() -> "Hello")
                .thenAcceptBoth(CompletableFuture.supplyAsync(() -> " World"),
                        (s1, s2) -> System.out.println(s1 + s2));
    }

    /**
     * CompletableFuture.allOf static method allows waiting for completion of all the Futures
     */
    @Test
    public void demo09() {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "Hello");
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> "Beautiful");
        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> "World");

        CompletableFuture<Void> combinedFuture = CompletableFuture.allOf(future1, future2, future3);

        try {
            combinedFuture.get();

            assertTrue(future1.isDone());
            assertTrue(future2.isDone());
            assertTrue(future3.isDone());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * The CompletableFuture.join() method is similar to the get method, but it throws an unchecked
     * exception. This makes it possible to use it as a method reference in the Stream.map()
     */
    @Test
    public void demo10() {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "Hello");
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> "Beautiful");
        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> "World");

        CompletableFuture<Void> combinedFuture = CompletableFuture.allOf(future1, future2, future3);

        try {
            String combined = Stream.of(future1, future2, future3)
                    .map(CompletableFuture::join)
                    .collect(Collectors.joining(" "));

            System.out.println(combined);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Handling Errors
     */
    @Test
    public void demo11() {
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("111");
            return 1;
        }).handle((f, e) -> {
            int i = 10 / 0;
            System.out.println("222");
            return f + 2;
        }).handle((f, e) -> {
            System.out.println("333");
            return f + 3;
        }).whenComplete((v, e) -> {
            System.out.println("complete");
            if (e == null) {
                System.out.println("calculate result： " + v);
            }
        }).exceptionally(e -> {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return null;
        });

        try {
            System.out.println(completableFuture.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
