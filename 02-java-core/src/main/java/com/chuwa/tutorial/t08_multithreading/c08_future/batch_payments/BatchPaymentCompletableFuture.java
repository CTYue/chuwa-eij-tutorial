package com.chuwa.tutorial.t08_multithreading.c08_future.batch_payments;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.chuwa.tutorial.t08_multithreading.c08_future.batch_payments.OrderProcessUtil.*;

/**
 * @author b1go
 * @date 4/14/23 12:41 AM
 */
public class BatchPaymentCompletableFuture {
    /**
     * 使用 CompletableFuture 版本:
     *
     * 优势：
     *
     * 性能更好，因为可以利用多线程并行执行查询订单、支付和发送邮件等操作，同时使用非阻塞的回调函数处理结果。
     * 使用线程池有效地管理线程资源。
     * 代码更加清晰和简洁，因为可以使用链式方法调用来组合和组装异步操作。
     * 提供了丰富的API来处理多个CompletableFuture的组合和异常处理。
     * 弊端：
     *
     * 代码复杂度相对较高，需要理解CompletableFuture的API和工作原理。
     * 在某些情况下，可能需要额外的同步或线程管理机制以确保正确的程序行为。
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        List<String> orderIds = Arrays.asList("order1", "order2", "order3");
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        /**
         * 并发查询订单：      ｜｜｜
         * 订单并发查询完成    -------
         * 并发支付订单：      ｜｜｜
         * 并发发送邮件：      ｜｜｜
         */
        List<CompletableFuture<Order>> orderFutures = new ArrayList<>();
        for (String orderId : orderIds) {
            CompletableFuture<Order> future = CompletableFuture.supplyAsync(() -> queryOrder(orderId), executorService);
            orderFutures.add(future);
        }

        List<CompletableFuture<Void>> emailFutures = new ArrayList<>();
        for (CompletableFuture<Order> orderFuture : orderFutures) {
            /**
             * 对于每个订单，makePayment操作和sendEmail操作会顺序执行，即先处理支付，然后发送邮件。
             * 但在处理多个订单时，这些操作会并发执行。这意味着，在处理一个订单的makePayment操作时，可能同时处理另一个订单的sendEmail操作。
             */
            CompletableFuture<Void> future = orderFuture
                    .thenApplyAsync(OrderProcessUtil::makePayment, executorService)
                    .thenAcceptAsync(paymentResult -> sendEmail(orderFuture.join(), paymentResult), executorService);
            emailFutures.add(future);
        }

        CompletableFuture.allOf(emailFutures.toArray(new CompletableFuture[0])).join();
        executorService.shutdown();
    }

    /**
     * 问题：
     * 在多个订单情况，future版本是先处理完所有order，再处理完所有payment，再处理完sendEmail?
     * 还是三个步骤同时并发处理？
     * 在使用Future版本的多个订单场景中，顺序是：
     *
     * 答案：
     * 先并发处理所有订单的查询（queryOrder）操作。
     * 然后在所有订单查询完成后，开始并发处理所有订单的支付（makePayment）操作。
     * 最后，在所有支付操作完成后，开始并发处理所有订单的发送邮件（sendEmail）操作。
     * 这意味着，在Future版本中，查询、支付和发送邮件操作是分阶段进行的。在每个阶段，操作会并发执行，但下一个阶段的操作需要等待上一个阶段的所有操作完成才能开始。
     *
     * 与之相比，CompletableFuture版本允许同时并发处理多个订单的查询、支付和发送邮件操作，这意味着，在处理一个订单的makePayment操作时，可能同时处理另一个订单的sendEmail操作。
     */

    /**
     * CompletableFuture 不仅语法简单，紧凑，性能还好。
     */
}
