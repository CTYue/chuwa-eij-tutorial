package com.chuwa.tutorial.t04_design_pattern.observer;

/**
 * @author Yanan Lyu
 * @date 2/22/22 8:46 PM
 * @description 具体观察者角色
 */
public class WeiXinUser implements Observer {
    private String name;

    public WeiXinUser(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        System.out.println(name + "-" + message);
    }
}
