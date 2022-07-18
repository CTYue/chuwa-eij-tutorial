package com.chuwa.tutorial.t08_multithreading.c02_basic_thread_handling;

/**
 * @author b1go
 * @date 5/13/22 12:06 AM
 * @description 守护线程是低优先级的线程，它的作用仅仅是为用户线程（非守护线程）提供服务。
 * 正是由于守护线程是为用户线程提供服务的，仅仅在用户线程处于运行状态时才需要守护线程。
 * 另外，一旦所有的用户线程都运行完毕，那么守护线程是无法阻止JVM退出的。即当程序只剩下守护线程的时候程序就会退出。
 *
 * 守护线程的作用类似在后台静默执行 ，最典型的就是JVM的垃圾回收机制, 这个就是一个守护线程。
 *
 * 在守护线程中，编写代码要注意：守护线程不能持有任何需要关闭的资源，例如打开文件等，因为虚拟机退出时，守护线程没有任何机会来关闭文件，这会导致数据丢失。
 */
public class Daemon {
    public static void main(String[] args) {
        // question: 当其它线程都运行完后，守护线程会结束么？
        // question: 当main 运行完后，守护线程会结束么？
        Thread tPpl = new Thread(new People());
        Thread tGod = new Thread(new God());
        Thread tGod2 = new Thread(new God());

        tGod.setDaemon(true);
        tGod.start();
        tPpl.start();
        tGod2.setDaemon(true);
        tGod2.start(); // 当只剩下守护进程时候，哪怕是有多个，jvm也会退出
        System.out.println("Main end");
    }
}

class People implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("ppl live");
        }

        System.out.println("People thread end");
    }
}

class God implements Runnable {
    @Override
    public void run() {
        while(true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("上帝守护你");
        }
    }
}
