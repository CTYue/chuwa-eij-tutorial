package com.chuwa.tutorial.t04_design_pattern.prototype.demo;

/**
 * @author ylyu
 * @version 1.0
 * @date 1/3/22 8:14 AM
 */
public class Client {
    public static void main(String[] args) throws CloneNotSupportedException {
        // 创建一个原型类对象
        RealizeType realizeType = new RealizeType();

        // 调用RealizeType类中的clone 方法进行对象的clone
        RealizeType clone = realizeType.clone();
        System.out.println("原型对象和科隆出来的是否是同一个对象？ " + (realizeType == clone));
    }
}
