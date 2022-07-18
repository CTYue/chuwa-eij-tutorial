package com.chuwa.tutorial.t08_multithreading.c07_executor_framework;

import java.util.concurrent.Executor;

/**
 * 1, create an Executor
 * 2, create a task
 * 3, use Executor to create a thread to execute task
 */
class ThreadExecutorExample {

    public static void main(String[] args) {
        // 1, create an Executor
        DumbExecutor myExecutor = new DumbExecutor();
        // 2, create a task
        MyTask myTask = new MyTask();
        // 3, use Executor to create a thread to execute task
        myExecutor.execute(myTask);
    }

    static class DumbExecutor implements Executor {
        // Takes in a runnable interface object
        @Override
        public void execute(Runnable runnable) {
            Thread newThread = new Thread(runnable);
            newThread.start();
        }
    }

    static class MyTask implements Runnable {
        @Override
        public void run() {
            System.out.println("Mytask is running now ...");
        }
    }

}
