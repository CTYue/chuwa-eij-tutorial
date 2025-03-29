package com.chuwa.tutorial.t08_multithreading.c06_util_concurrent_locks;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class ConcurrentCollection {
private static final int THREAD_COUNT = 10;
    private static final int OPERATIONS_PER_THREAD = 1000;

    public static void main(String[] args) throws InterruptedException {
//        ConcurrentHashMap<Integer, Integer> map = new ConcurrentHashMap<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);

        for(int i=0; i<5; i++) {
            executorService.submit(() -> {
                for (int j = 0; j < OPERATIONS_PER_THREAD; j++) {
                    map.put(j, j);
                }});
            executorService.submit(() -> {
                for (int j = 0; j < OPERATIONS_PER_THREAD; j++) {
                    map.putIfAbsent(j, j);
                }
            });
        }

        executorService.awaitTermination(3, TimeUnit.SECONDS);

        System.out.println("Final map size: " + map.size());
        map.forEach((k,v)->{
            System.out.println(k + ": "+ v);
        });
        System.out.println("Expected map size: " + 1000);
    }
}
