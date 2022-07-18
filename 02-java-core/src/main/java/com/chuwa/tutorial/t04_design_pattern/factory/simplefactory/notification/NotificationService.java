package com.chuwa.tutorial.t04_design_pattern.factory.simplefactory.notification;

/**
 * @author Yanan Lyu
 * @date 2021-12-23-1:49 AM
 * https://www.geeksforgeeks.org/factory-method-design-pattern-in-java/
 */
public class NotificationService {
    public static void main(String[] args) {
        NotificationFactory notificationFactory = new NotificationFactory();
        Notification notification = notificationFactory.createNotification("SMS");
        notification.notifyUser();
    }
}
