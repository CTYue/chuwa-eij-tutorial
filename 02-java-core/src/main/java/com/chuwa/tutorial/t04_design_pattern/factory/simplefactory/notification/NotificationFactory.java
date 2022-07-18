package com.chuwa.tutorial.t04_design_pattern.factory.simplefactory.notification;

/**
 * @author Yanan Lyu
 * @date 2021-12-23-1:48 AM
 */
public class NotificationFactory {
    public Notification createNotification(String channel) {
        switch (channel) {
            case "SMS":
                return new SMSNotification();
            case "EMAIL":
                return new EmailNotification();
            case "PUSH":
                return new PushNotification();
            default:
                return null;
        }
    }
}
