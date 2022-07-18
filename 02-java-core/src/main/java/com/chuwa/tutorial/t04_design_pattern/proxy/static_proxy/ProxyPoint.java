package com.chuwa.tutorial.t04_design_pattern.proxy.static_proxy;

/**
 * @author ylyu
 * @version 1.0
 * @date 1/5/22 11:00 PM
 * @description 代售点
 */
public class ProxyPoint implements SellTickets {
    // 代售点
    private final TrainStation trainStation = new TrainStation();

    @Override
    public void sell() {
        System.out.println("proxy charge service fee");
        trainStation.sell();
    }
}
