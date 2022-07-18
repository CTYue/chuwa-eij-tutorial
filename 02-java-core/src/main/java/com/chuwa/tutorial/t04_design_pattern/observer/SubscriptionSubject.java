package com.chuwa.tutorial.t04_design_pattern.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yanan Lyu
 * @date 2/22/22 8:34 PM
 * @description 具体主题角色类
 */
public class SubscriptionSubject implements Subject{

    // 定义一个集合，用来存储多个观察者对象
    private List<Observer> weiXinUserList = new ArrayList<>();

    @Override
    public void attach(Observer observer) {
        weiXinUserList.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        weiXinUserList.remove(observer);
    }

    @Override
    public void notify(String message) {
        // 遍历集合
        for (Observer observer :
                weiXinUserList) {
            // 调用观察者对象中的方法
            observer.update(message);
        }
    }
}
