package com.chuwa.tutorial.t09_reactive.c01_reactive_stream;

import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;

/**
 * @author b1go
 * @date 12/27/22 1:50 PM
 */
public class ReactiveProcessor extends SubmissionPublisher<String> implements Flow.Processor<String, String> {
    private Flow.Subscription subscription;

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        System.out.println("建立订阅关系");
        this.subscription = subscription;
        subscription.request(1); // 第一次需要
    }

    @Override
    public void onNext(String item) {
        System.out.println("Processor 接收数据: " + item);
        //中间处理, 数据转发给订阅者
        this.submit(item.toUpperCase());
        //背压实现的核心
        subscription.request(10); //背压
    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println("Processor 出现异常");
        throwable.printStackTrace();
        this.subscription.cancel();
    }

    @Override
    public void onComplete() {
        System.out.println("数据接收完成");
    }
}
