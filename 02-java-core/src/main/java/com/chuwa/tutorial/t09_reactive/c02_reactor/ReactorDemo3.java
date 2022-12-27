package com.chuwa.tutorial.t09_reactive.c02_reactor;

import reactor.core.publisher.Flux;

/**
 * @author b1go
 * @date 12/27/22 2:15 PM
 */
public class ReactorDemo3 {
    /**
     * 输入： hello guys, i am richard welcome to chuwa
     * output: abcdefg....
     */
    public static void main(String[] args) {
        String src = "hello guys, i am richard welcome to chuwa";
        Flux.fromArray(src.split(" "))
                .flatMap(i -> Flux.fromArray(i.split("")))
                .distinct()
                .sort()
                .subscribe(System.out::println);
    }
}
