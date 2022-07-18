package com.chuwa.tutorial.t04_design_pattern.proxy.jdk_proxy;

/**
 * @author ylyu
 * @version 1.0
 * @date 1/5/22 11:17 PM
 */
public class Client {
    public static void main(String[] args) {
        ProxyFactory factory = new ProxyFactory();
        SellTickets proxyObject = factory.getProxyObject();
        proxyObject.sell();

//        System.out.println(proxyObject.getClass());
//        while(true){}
    }
}
