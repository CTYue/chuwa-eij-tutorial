package com.chuwa.tutorial.t04_design_pattern.singleton.innerstaticclass;

/**
 * @author ylyu
 * @version 1.0
 * @date 1/1/22
 * @description 静态内部类单例模式
 */
public class Singleton {
    /**
     * 静态内部类单例模式中实例由内部类创建，由于 JVM 在加载外部类的过程中, 是不会加载静态
     * 内部类的, 只有内部类的属性/方法被调用时才会被加载, 并初始化其静态属性。静态属性由于被
     * static 修饰，保证只被实例化一次，并且严格保证实例化顺序。
     */

    // make constructor be private
    private Singleton() {
    }

    // define a inner static class
    private static class SingletonHolder {
        private static final Singleton INSTANCE = new Singleton();
    }

    // provide public access method
    public static Singleton getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
