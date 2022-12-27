package com.chuwa.tutorial.t09_reactive.c02_reactor;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * @author b1go
 * @date 12/27/22 2:06 PM
 */
public class ReactorDemo {
    public static void main(String[] args) {
        //创建方式
        Mono.empty().subscribe(System.out::println);

        Mono.just("hello mono").subscribe(System.out::println);

        // Flux创建方式0-n元素序列
        Flux.just(1,2,3,4,5,6).subscribe(System.out::println);
        Flux.fromIterable(Arrays.asList("a", "b", "c")).subscribe(System.out::println);
        Flux.fromArray(new String[] {"a", "b", "c"}).subscribe(System.out::println);
        Flux.fromStream(Stream.of(1,2,3,4,5,6)).subscribe(System.out::println);
        Flux.range(1, 100).subscribe(System.out::println);
    }
}
