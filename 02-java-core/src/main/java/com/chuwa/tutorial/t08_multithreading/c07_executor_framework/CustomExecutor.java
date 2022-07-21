package com.chuwa.tutorial.t08_multithreading.c07_executor_framework;

import java.util.*;
import java.util.concurrent.Executor;

public class CustomExecutor implements Executor {

    /**
     * max worker size
     */
    private static final int MAX_WORKER_NUMBERS = 30;

    /**
     * tasks queue
     */
    private final Deque<Runnable> tasks = new LinkedList<>();

    /**
     * worker list
     */
    private final List<Worker> workers = Collections.synchronizedList(new ArrayList<>());


    /**
     * Initialize thread pool
     */
    public CustomExecutor(int num) {
        initializeWorkers(Math.min(num, MAX_WORKER_NUMBERS));
    }

    /**
     * initialize workers
     */
    private void initializeWorkers(int num) {
        for (int i = 0; i < num; i++) {
            Worker worker = new Worker();
            // add worker to list
            workers.add(worker);

            // start worker thread
            new Thread(worker).start();
        }
    }

    /**
     * Thread pool add a task to task queue
     *
     * @param task
     */
    @Override
    public void execute(Runnable task) {
        if (task == null) {
            throw new NullPointerException();
        }

        // add task to task queue and notify worker
        synchronized (tasks) {
            tasks.addLast(task);
            tasks.notify();

        }
    }

    /**
     * close thread pool
     */
    public void shutdown() {
        for (Worker worker : workers) {
            worker.shutdown();
        }
    }

    /**
     * add worker
     *
     * @param num
     */
    public void addWorkers(int num) {
        synchronized (tasks) {
            if (num + workers.size() > MAX_WORKER_NUMBERS) {
                num = MAX_WORKER_NUMBERS - workers.size();
            }
            initializeWorkers(num);
        }
    }

    /**
     * decrease workers
     *
     * @param num
     */
    public void removeWorker(int num) {
        synchronized (tasks) {
            if (num >= workerSize()) {
                throw new IllegalArgumentException("exceed current worker size");
            }
            for (int i = 0; i < num; i++) {
                Worker worker = workers.get(i);
                if (worker != null) {
                    //
                    worker.shutdown();
                    workers.remove(i);
                }
            }
        }

    }

    /**
     * get current worker size
     */
    public int workerSize() {
        return workers.size();
    }

    /**
     * worker get task from task queue and execute task
     */
    class Worker implements Runnable {
        private volatile boolean running = true;

        @Override
        public void run() {
            Runnable task = null;

            // If there are unfinished tasks in the task queue, continue to execute the task
            // until all tasks are completed, and then close the thread
            while (running || task != null) {
                // execute task
                if (task != null) {
                    task.run();
                }

                synchronized (tasks) {
                    if (tasks.isEmpty() && running) {
                        try {
                            tasks.wait();
                        } catch (InterruptedException e) {
                            // return when current thread is interrupted
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                    // get a task from task queue
                    task = tasks.isEmpty() ? null : tasks.removeFirst();
                }
            }
        }

        /**
         * shut down worker
         */
        public void shutdown() {
            running = false;

            synchronized (tasks) {
                // notify worker to shut down
                tasks.notify();
            }
        }
    }

}
