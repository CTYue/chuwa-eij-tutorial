# Thread

## Interview questions
* What is the difference between start() and run()?
* How to create a new thread?
* What kind of locks do you know? what the details of them?
* What is thread pool? what is the advantage and how to create a thread pool?
* What is callable? what is the diff between callable and runnable?
* What is future and competableFuture? give me the example in your experience.

## Tutorial
https://www.liaoxuefeng.com/wiki/1252599548343744/1304521607217185

## Creation
* implements Runnable/Callable
* extends Thread

### Steps to create and run a thread
1. implement Runnable/callable or extends Thread.
2. override run()
3. t = new MyThread() or t = new Thread(new MyRunnable())
4. t.start()
   1. t.start() will call run()

Actually Thread does also implement Runnable.

Question: can we use lambda for runnable?
* use the @FunctionalInterface
* only one method: public abstract void run();
* so we can use lambda function

## Basic Thread Handling
* `t.join()`
  * The thread A which call t.join() will suspend its execution and t thread would start to execute. A will continue after t is executed successfully.
* `t.setDaemon(true)`
  * A daemon thread runs in the background but as soon as the main application thread exits, all daemon threads are killed by the JVM.
  * Note that in case a spawned thread isn’t marked as a daemon then even if the main thread finishes execution, JVM will wait for the spawned thread to finish before tearing down the process.
* `Thread.sleep(1000)`
  * the thread which call Thread.sleep(1000) would sleep
* `t.interrupt()`
  * can interrupt sleep()
  * can not interrupt while(true)
  * can interrupt !isInterrupted()

## Executor Framework
The Executor requires implementing classes to define a method execute(Runnable runnable) which takes in an object of interface Runnable.
* Task Submission
* Task Execution

An executor has the following stages in its lify-cycle:
* Running
* Shutting Down
* Terminated

### Thread Pools
A thread pool consists of homogenous worker threads that are assigned to execute tasks. Once a worker thread finishes a task, it is returned to the pool. Usually, thread pools are bound to a queue from which tasks are dequeued for execution by worker threads.

Using a thread pool immediately alleviates from the ails of manual creation of threads.

* There's no latency when a request is received and processed by a thread because no time is lost in creating a thread.
* The system will not go out of memory because threads are not created without any limits
* Fine tuning the thread pool will allow us to control the throughput of the system. We can have enough threads to keep all processors busy but not so many as to overwhelm the system.
* The application will degrade gracefully if the system is under load.

Types of Thread Pools
* newFixedThreadPool: This type of pool has a fixed number of threads and any number of tasks can be submitted for execution. Once a thead finishes a task, it can reused to execute another task from the queue.
* newSingleThreadExecutor: This executor uses a single worker thread to take tasks off of queue and execute them. If the thread dies unexpectedly, then the executor will replace it with a new one.
* newCachedThreadPool: This pool will create new threads as required and use older ones when they become available. However, it'll terminate threads that remain idle for a certain configurable period of time to conserve memory. This pool can be a good choice for short-lived asynchronous tasks.
* newScheduledThreadPool: This pool can be used to execute tasks periodically or after a delay.

## Status
* New：新创建的线程，尚未执行；
* Runnable：运行中的线程，正在执行run()方法的Java代码；
* Blocked：运行中的线程，因为某些操作被阻塞而挂起；
* Waiting：运行中的线程，因为某些操作在等待中；
* Timed Waiting：运行中的线程，因为执行sleep()方法正在计时等待；
* Terminated：线程已终止，因为run()方法执行完毕。

## 线程终止
* 线程正常终止：run()方法执行到return语句返回；
* 线程意外终止：run()方法因为未捕获的异常导致线程终止；
* 对某个线程的Thread实例调用stop()方法强制终止（强烈不推荐使用）。

## 中断线程
