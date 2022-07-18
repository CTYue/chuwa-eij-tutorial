package com.chuwa.tutorial.t04_design_pattern.factory.simplefactory.notification;

/**
 * @author Yanan Lyu
 * @date 2021-12-23-1:47 AM
 */
public class PushNotification implements Notification {
    @Override
    public void notifyUser() {
        System.out.println("Sending a push notification");
    }
}
