package com.chuwa.tutorial.t09_reactive.c01_reactive_stream;

import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;

/**
 * @author b1go
 * @date 12/27/22 1:42 PM
 * https://www.bilibili.com/video/BV1dv4y1g75y?p=10&vd_source=310561eab1216a27f7accf859bf7f6d9
 */
public class ReactiveStreamDemo2 {
    public static void main(String[] args) throws InterruptedException {
        // 1. 创建发布者
        SubmissionPublisher publisher = new SubmissionPublisher();

        //2.创建处理器
        ReactiveProcessor processor = new ReactiveProcessor();

        //3. 发布者与处理器建立订阅关系
        publisher.subscribe(processor);

        //4. 最终创建一个订阅者
        Flow.Subscriber subscriber = new Flow.Subscriber() {

            Flow.Subscription subscription;

            @Override
            public void onSubscribe(Flow.Subscription subscription) {
                System.out.println("建立订阅关系");
                this.subscription = subscription;
                subscription.request(1); // 第一次需要
            }

            @Override
            public void onNext(Object item) {
                System.out.println("接收数据" + item);
                //业务处理
                subscription.request(10); //背压
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("错误发生了");
            }

            @Override
            public void onComplete() {
                System.out.println("数据接收完成");
            }
        };

        //5. 中间处理器与订阅者建立订阅关系
        processor.subscribe(subscriber);

        //4. 发送数据
        for (int i = 0; i < 500; i++) {
            System.out.println("发布数据 = " + i);
            publisher.submit("bole: " + i);
        }

        publisher.close();
        Thread.currentThread().join(10000);
    }
}
