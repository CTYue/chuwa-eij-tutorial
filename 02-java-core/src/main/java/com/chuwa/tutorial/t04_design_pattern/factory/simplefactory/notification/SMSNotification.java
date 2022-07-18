package com.chuwa.tutorial.t04_design_pattern.factory.simplefactory.notification;

/**
 * @author Yanan Lyu
 * @date 2021-12-23-1:44 AM
 */
public class SMSNotification implements Notification {
    @Override
    public void notifyUser() {
        System.out.println("Sending an SMS notification");
    }
}
