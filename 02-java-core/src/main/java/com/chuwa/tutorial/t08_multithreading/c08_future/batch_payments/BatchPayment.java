package com.chuwa.tutorial.t08_multithreading.c08_future.batch_payments;

import java.util.Arrays;
import java.util.List;

import static com.chuwa.tutorial.t08_multithreading.c08_future.batch_payments.OrderProcessUtil.*;

/**
 * @author b1go
 * @date 4/14/23 12:35 AM
 */
public class BatchPayment {

    /**
     * 单线程版本:
     *
     * 优势：
     *
     * 代码简单，易于理解和维护。
     * 无需担心线程同步或并发问题。
     * 弊端：
     *
     * 性能较差，因为所有操作都在一个线程中执行，而查询订单、支付和发送邮件等操作可能需要较长时间。当处理大量订单时，这种实现可能导致不可接受的延迟。
     */
    public static void main(String[] args) {
        List<String> orderIds = Arrays.asList("order1", "order2", "order3");

        /**
         * 查询订单：            ｜
         * 第1个订单查询完成    -------
         * 支付订单：            ｜
         * 订单支付完成       -------
         * 发送邮件：            ｜
         * 邮件发送完成       -------
         *
         * 查询订单：            ｜
         * 第2个订单查询完成    -------
         * 支付订单：            ｜
         * 订单支付完成       -------
         * 发送邮件：            ｜
         * 邮件发送完成       -------
         *
         * 查询订单：            ｜
         * 第3个订单查询完成    -------
         * 支付订单：            ｜
         * 订单支付完成       -------
         * 发送邮件：            ｜
         * 邮件发送完成       -------
         */
        for (String orderId : orderIds) {
            Order order = queryOrder(orderId);
            boolean paymentResult = makePayment(order);
            sendEmail(order, paymentResult);
        }
    }



}

