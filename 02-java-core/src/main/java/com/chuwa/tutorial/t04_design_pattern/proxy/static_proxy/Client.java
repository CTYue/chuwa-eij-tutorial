package com.chuwa.tutorial.t04_design_pattern.proxy.static_proxy;

/**
 * @author ylyu
 * @version 1.0
 * @date 1/5/22 11:05 PM
 */
public class Client {
    public static void main(String[] args) {
        ProxyPoint proxyPoint = new ProxyPoint();
        proxyPoint.sell();
    }
}
