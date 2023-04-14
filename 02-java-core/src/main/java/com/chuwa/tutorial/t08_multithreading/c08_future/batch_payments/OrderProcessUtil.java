package com.chuwa.tutorial.t08_multithreading.c08_future.batch_payments;

/**
 * @author b1go
 * @date 4/14/23 12:37 AM
 */
public class OrderProcessUtil {
    public static Order queryOrder(String orderId) {
        // Query order from database or other source
        return new Order(orderId, 100.0);
    }

    public static boolean makePayment(Order order) {
        // Process payment for the order
        return true;
    }

    public static void sendEmail(Order order, boolean paymentResult) {
        // Send email notification based on the payment result
    }
}
