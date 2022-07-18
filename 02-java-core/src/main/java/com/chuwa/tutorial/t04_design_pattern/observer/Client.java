package com.chuwa.tutorial.t04_design_pattern.observer;

/**
 * @author Yanan Lyu
 * @date 2/22/22 8:47 PM
 * @description 在 Java 中,通过 java.util.Observable 类和 java.util.Observer 接口定义了观察者模
 * 式，只有实现它们的子类就可以编写观察者模式实例。
 */
public class Client {
    public static void main(String[] args) {
        // 创建公众号对象
        SubscriptionSubject subject = new SubscriptionSubject();

        // 订阅公众号
        subject.attach(new WeiXinUser("孙悟空"));
        subject.attach(new WeiXinUser("孙悟能"));
        subject.attach(new WeiXinUser("孙悟净"));

        // 公众号更新, 发出消息给订阅者（观察者对象）
        subject.notify("新文章发布了");
    }
}
