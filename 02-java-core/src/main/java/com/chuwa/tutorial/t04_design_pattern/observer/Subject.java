package com.chuwa.tutorial.t04_design_pattern.observer;

/**
 * @author Yanan Lyu
 * @date 2/22/22 8:29 PM
 * @description  抽象主题
 */
public interface Subject {
    // 添加订阅者对象
    void attach(Observer observer);
    void detach(Observer observer);
    void notify(String message);
}
