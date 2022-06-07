package com.chuwa.tutorial.java8.features.lambda;

@FunctionalInterface
public interface Baz {
    String method(String string);

    default String defaultBaz() {
        String s = "default Baz method";
        System.out.println(s);
        return s;
    }

    default String defaultCommon() {
        String s = "defaultCommon from Baz";
        System.out.println(s);
        return s;
    };
}
