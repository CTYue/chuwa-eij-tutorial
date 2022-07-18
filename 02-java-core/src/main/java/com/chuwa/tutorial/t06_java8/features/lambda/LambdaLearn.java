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
//        Foo fooByIC = new Foo();
        Foo fooByIC = new Foo() {
            @Override
            public String method(String string) {
                return string + " from Foo";
            }
        };

        String hello = fooByIC.method("hello");
        System.out.println(hello);
    }

    @Test
    public void lambdaFoo() {
        Foo foo = parameter -> parameter + " from Foo";

        String hello = foo.method("hello");
        System.out.println(hello);
    }

    @Test
    public void lambdaFoo2() {
        Foo foo = parameter -> parameter.toUpperCase() + " from Foo";

        String hello = foo.method("hello");
        System.out.println(hello);
    }

    /**
     * To solve this problem, we have two options. The first option is to use methods with different names:
     * String processWithCallable(Callable<String> c) throws Exception;

     * String processWithSupplier(Supplier<String> s);
     * @throws Exception
     */
    @Test
    public void testOverload() throws Exception {
        Processor processor = new ProcessorImpl();
//        String result = processor.process(() -> "abc");
//        String result = processor.process((Callable<String>) () -> "abc");
    }

    @Test
    public void testFinal() {
        final String localVariable = "Local";
        Foo foo = parameter -> {
            return parameter + " " + localVariable;
        };

        System.out.println(foo.method("hello"));
    }

    /**
     * Use “Effectively Final” Variables
     * 当variable只赋值一次，没有任何变动的时候，Java默认是final。
     */
    @Test
    public void testEffectivelyFinal() {
        String localVariable = "Local";
        Foo foo = parameter -> {
            return parameter + " " + localVariable;
        };

        System.out.println(foo.method("hello"));
    }

    /**
     * 换object会报错
     */
    @Test
    public void testFinal2() {
        String localVariable = "Local";
        localVariable = "LOCAL";

        Foo foo = parameter -> {
//            return parameter + " " + localVariable;
            return parameter;
        };

        System.out.println(foo.method("hello"));
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

        System.out.println(foo.method("hello"));
    }
}
