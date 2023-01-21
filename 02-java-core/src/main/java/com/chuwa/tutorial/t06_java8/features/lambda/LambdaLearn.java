package com.chuwa.tutorial.t06_java8.features.lambda;


import com.chuwa.tutorial.t00_common.pojos.Employee;
import com.chuwa.tutorial.t00_common.utils.EmployeeData;
import org.junit.Test;

import java.util.List;

public class LambdaLearn {

    /**
     *
     * https://www.baeldung.com/java-8-lambda-expressions-tips
     *
     * 1， 只能有一个方法。可以有多个default方法（因为default方法被实现了）
     * 2， Avoid Overloading Methods With Functional Interfaces as Parameters， 如果有overloading, 可以用Cast
     *
     * By using the @FunctionalInterface annotation, the compiler will trigger an error in response to any attempt to
     * break the predefined structure of a functional interface. It is also a very handy tool to make our application
     * architecture easier to understand for other developers.
     */

    @Test
    public void overrideFoo() {
        // Before Java 8, we can use Anonymous Class to override this method
        // Interface var = new Class
        // List<Integer> var = new ArrayList<>()
        // Foo 这个interface有一个abstract method, 所以在Anonymous Class里需要override来提供method body
        Foo fooByIC = new Foo() {
            @Override
            public String aMethod(String string) {
                return string + " from Foo";
            }
        };

        String hello = fooByIC.aMethod("hello");
        System.out.println(hello);
    }

    @Test
    public void lambdaFoo() {
        // Foo.aMethod() 是abstract method,缺少method body. lambda 提供method body.
        // 比Anonymous class 简洁很多。
        Foo foo = parameter -> parameter + " from Foo";

        String hello = foo.aMethod("hello");
        System.out.println(hello);
    }

    @Test
    public void lambdaFoo2() {
        // 可以提供任何method body
        Foo foo = parameter -> parameter.toUpperCase() + " from Foo";

        String hello = foo.aMethod("hello");
        System.out.println(hello);
    }

    @Test
    public void testFinal() {
        final String localVariable = "Local";
        Foo foo = parameter -> {
            return parameter + " " + localVariable;
        };

        System.out.println(foo.aMethod("hello"));
    }

    /**
     * Use “Effectively Final” Variables
     * 当variable只赋值一次，没有任何变动的时候，Java默认是final。
     * 注意，在lambda expression的前后都不能被改变
     */
    @Test
    public void testEffectivelyFinal() {
        String localVariable = "Local";
        Foo foo = parameter -> {
            return parameter + " " + localVariable;
        };

        System.out.println(foo.aMethod("hello"));
    }

    /**
     * 换object会报错，因为换了内存地址
     */
    @Test
    public void testFinal21() {
        String localVariable = "Local";
        localVariable = "LOCAL"; // 新的内存地址

        Foo foo = parameter -> {
//            return parameter + " " + localVariable;
            return parameter;
        };

        System.out.println(foo.aMethod("hello"));
    }

    @Test
    public void testFinal22() {
        String localVariable = "Local";

        Foo foo = parameter -> {
//            return parameter + " " + localVariable;
            return parameter;
        };

        localVariable = "LOCAL"; // 新的内存地址

        System.out.println(foo.aMethod("hello"));
    }

    /**
     * Object 的set方法不会报错
     */
    @Test
    public void testFinal3() {
        List<Employee> employees = EmployeeData.getEmployees();

        Employee employee = employees.get(0);
        employee.setAge(55);
        Foo foo = parameter -> {
            return parameter + " " + employee;
        };

        System.out.println(foo.aMethod("hello"));
    }
}
