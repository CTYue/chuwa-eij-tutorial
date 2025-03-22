package com.chuwa.tutorial.t04_design_pattern.singleton.destroysingleton;

import java.io.Serializable;

/**
 * @author ylyu
 * @version 1.0
 * @date 1/1/22
 * @description 静态内部类单例模式
 */
public class Singleton implements Serializable {

    // make constructor be private
    private Singleton() {
    }

    // define a inner static class
    // Final: ensures INSTANCE will NOT be modified after line 20
    // SingletonHolder method works as a helper/utility method which instantiate a single "Singleton" object
    private static class SingletonHolder {
        private static final Singleton INSTANCE = new Singleton();
    }

    // provide public access method
    // this getInstance method ensures user can ONLY get the one true INSTANCE from here
    public static Singleton getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /**
     * 下面是为了解决序列化反序列化破解单例模式
     */
    public Object readResolve() {
        return SingletonHolder.INSTANCE;
    }
}
