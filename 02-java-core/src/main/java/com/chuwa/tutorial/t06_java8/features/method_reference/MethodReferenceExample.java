package com.chuwa.tutorial.t06_java8.features.method_reference;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * @author Yanan Lyu
 * @date 5/9/22 4:03 PM
 */

public class MethodReferenceExample {
    public static void main(String[] args) {
        // 1. 静态方法引用
        List<Person> people = Arrays.asList(
                new Person("Alice", 30),
                new Person("Bob", 25),
                new Person("Charlie", 35));

        // 使用Lambda表达式
        people.sort((p1, p2) -> Person.compareByName(p1, p2));

        // 使用静态方法引用
        people.sort(Person::compareByName);

        // 2. 实例方法引用（特定对象的实例方法）
        Comparator<Person> byAgeComparator = Comparator.comparingInt(Person::getAge);
        people.sort(byAgeComparator);

        // 3. 类的实例方法引用
        // 不要尝试理解Function,将会被stream使用。
        Function<Person, String> getNameFunction = Person::getName;
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
        names.sort(String::compareToIgnoreCase);

        // 4. 构造方法引用
        // 不要尝试理解BiFunction,将会被stream使用。
        BiFunction<String, Integer, Person> personCreator = Person::new;
        Person newPerson = personCreator.apply("David", 40);

        System.out.println(people);
    }
}

class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public static int compareByName(Person p1, Person p2) {
        return p1.name.compareTo(p2.name);
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + " (" + age + ")";
    }
}
