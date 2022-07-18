package com.chuwa.tutorial.t04_design_pattern.singleton.doublecheck;

/**
 * Double Check
 *
 * @author Yanan Lyu
 * @date 5/24/21 11:09 PM
 */
public class Singleton {

    /**
     * volatile 使得修改值立即更新到主程序
     * <p>
     * 双重检测锁模式看上去完美无缺，其实是存在问题，在多线程的情况下，可能会出现空指针问题，出现问
     * 题的原因是JVM在实例化对象的时候会进行优化和指令重排序操作。
     * 要解决双重检查锁模式带来空指针异常的问题，只需要使用 volatile 关键字, volatile 关
     * 键字可以保证可见性和有序性。
     */

    // 1, static volatile variable
    private static volatile Singleton instance;

    // 2, make constructor be private
    private Singleton() {
    }

    // 3. static synchronized getInstance method
    public static Singleton getInstance() {

        // 4, make sure thread safe
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }

        return instance;
    }
}
