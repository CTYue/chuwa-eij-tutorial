package com.chuwa.tutorial.t08_multithreading.c08_future.batch_payments;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static com.chuwa.tutorial.t08_multithreading.c08_future.batch_payments.OrderProcessUtil.*;

/**
 * @author b1go
 * @date 4/14/23 12:38 AM
 */
public class BatchPaymentFuture {

    /**
     * 使用 Future 版本:
     *
     * 优势：
     *
     * 性能较好，因为可以利用多线程并行执行查询订单、支付和发送邮件等操作。
     * 使用线程池有效地管理线程资源。
     * 弊端：
     *
     * 代码较复杂，需要处理线程池、Future对象和异常处理。
     * 仍然需要使用阻塞的get()方法等待结果，这可能导致线程阻塞，从而影响程序性能。
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        /**
         * 并发查询订单：      ｜｜｜
         * 订单并发查询完成    -------
         * 并发支付订单：      ｜｜｜
         * 订单支付完成       -------
         * 并发发送邮件：      ｜｜｜
         * 邮件发送完成       -------
         */
        List<String> orderIds = Arrays.asList("order1", "order2", "order3");
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        List<Future<Order>> orderFutures = new ArrayList<>();
        for (String orderId : orderIds) {
            Future<Order> future = executorService.submit(() -> queryOrder(orderId));
            orderFutures.add(future);
        }

        List<Future<Boolean>> paymentFutures = new ArrayList<>();
        for (Future<Order> orderFuture : orderFutures) {
            Order order = orderFuture.get();
            Future<Boolean> future = executorService.submit(() -> makePayment(order));
            paymentFutures.add(future);
        }

        for (int i = 0; i < paymentFutures.size(); i++) {
            boolean paymentResult = paymentFutures.get(i).get();
            sendEmail(orderFutures.get(i).get(), paymentResult);
        }

        executorService.shutdown();

    }

}
