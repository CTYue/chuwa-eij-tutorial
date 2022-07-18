package com.chuwa.tutorial.t04_design_pattern.proxy.cglib_proxy;

/**
 * @author ylyu
 * @version 1.0
 * @date 1/6/22 10:40 AM
 */
public class Client {
    public static void main(String[] args) {
        ProxyFactory factory = new ProxyFactory();
        TrainStation proxyObject = factory.getProxyObject();
        proxyObject.sell();
    }
}
