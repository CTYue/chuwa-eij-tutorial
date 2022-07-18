package com.chuwa.tutorial.t06_java8.features.stream_api;

import com.chuwa.tutorial.t00_common.pojos.Employee;
import com.chuwa.tutorial.t00_common.utils.EmployeeData;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Collections 讲的是数据，Stream讲的是计算（CPU）
 * 1. Stream 自己不会存储元素
 * 2. Stream 不会改变源对象，相反，他们会返回一个持有结果的新stream
 * 3. Stream操作是延迟执行的，这意味着他们会等到需要结果的时候才执行。
 * Stream执行流程：
 * 1. 创建Stream： 获取一个数据源
 * 2. 中间操作： 一个操作链： 对数据进行处理
 * 3. 终止操作： 一旦执行中止操作，则会开始执行中间操作链，并产生结果。之后，不会再被使用。
 */
public class StreamApiIntermediateOperation {
    /**
     * 创建Stream的中间操作
     * <p>
     * 一，筛选
     * 1. filter(Predicate p) - 接受lambda, 从流中排出某些元素
     * 2. limit(n) - 截断流，使其元素不超过给定的数量
     * 3. skip(n) - 跳过前n个元素
     * 4. distinct() - 筛选，通过元素的hashcode(), equals()去除重复元素
     * <p>
     * 二，映射
     * 1, map(function f) element -> black box(f) -> new element
     * 2, flatMap(function f)
     * 三， 排序
     * 1, sorted
     */
    @Test
    public void testFilter() {
        List<Integer> col = Arrays.asList(1, 2, 3, 4, 5);
        Stream<Integer> stream = col.stream();
        stream.filter(t -> t % 2 == 0).forEach(System.out::println);

        // 获取员工姓名长度大于3的员工姓名
        List<Employee> employees = EmployeeData.getEmployees();
        Stream<String> nameStream = employees.stream().map(Employee::getName);
        nameStream.filter(name -> name.length() > 6).forEach(System.out::println);
    }

    @Test
    public void testLimit() {
        List<Integer> col = Arrays.asList(1, 2, 3, 4, 5);
        col.stream().limit(3).forEach(System.out::println);
    }

    @Test
    public void testSkip() {
        List<Integer> col = Arrays.asList(1, 2, 3, 4, 5);
        col.stream().skip(2).forEach(System.out::println);
    }

    @Test
    public void testDistinct() {
        int[] arr = new int[]{1, 2, 3, 4, 4, 2, 5};
        IntStream st = Arrays.stream(arr);
        st.distinct().forEach(System.out::println);
    }

    // https://www.youtube.com/watch?v=jPjOW6f1_EA&list=PLmOn9nNkQxJH0qBIrtV6otI0Ep4o2q67A&index=678

    /**
     * 映射
     * map(Function f)： 使用函数 f 对数据进行处理
     */
    @Test
    public void testMap() {
        // map(Function f) - 接收一个函数作为参数，将元素转换成其它形式或提取信息，该函数会被
        List<String> list = Arrays.asList("aa", "bb", "cc", "dd");
        list.stream().map(String::toUpperCase).forEach(System.out::println);
    }

    @Test
    public void testFlatMap() {
        List<String> list = Arrays.asList("aa", "bb", "cc", "dd");

        System.out.println("****** 使用map ********");
        // flatMap(Function f) - 接收一个函数作为参数，将流中的每个值换成另外一个流，然后把所有流链接成一个流
        // 返回的是一个stream对象
        Stream<Stream<Character>> streamStream = list.stream().map(StreamApiIntermediateOperation::fromStringToStream);
        streamStream.forEach(stream -> stream.forEach(System.out::println));

        System.out.println("**** 使用flatMap *****");

        // Stream<Stream<Character>  --> Stream<Character>
        Stream<Character> characterStream = list.stream().flatMap(StreamApiIntermediateOperation::fromStringToStream);
        characterStream.forEach(System.out::println);
    }

    /* 将字符串中的多个字符集合转换为对应的Stream实例 */
    public static Stream<Character> fromStringToStream(String str) {
        ArrayList<Character> list = new ArrayList<>();
        for (Character c : str.toCharArray()) {
            list.add(c);
        }

        return list.stream();
    }

    /**
     * 把两个string里面的数字相加并求和。 1 + 2+ 3 + ... + 9 + 10
     */
    @Test
    public void testFlatMap2() {
        List<String> lists1 = Arrays.asList("1", "2", "33", "4", "5");
        List<String> lists2 = Arrays.asList("6", "7", "8", "9", "10");
        List<List<String>> listsOfStrings = new ArrayList<>();
        listsOfStrings.add(lists1);
        listsOfStrings.add(lists2);

        // listOfStrings是 [[1, 2, 3, 4, 5], [6, 7, 8, 9, 10]]
        System.out.println("listsOfStrings: " + listsOfStrings);

        System.out.println("****** 使用map ********");
        // 由于map返回的是list，所以Stream里是List<Integer>
        Stream<List<Integer>> listStream = listsOfStrings.stream().map(nums -> nums.stream().map(Integer::parseInt).collect(Collectors.toList()));
        // 注意输出的是两个数组
        listStream.forEach(System.out::println);
        Stream<List<Integer>> listStream2 = listsOfStrings.stream().map(nums -> nums.stream().map(Integer::parseInt).collect(Collectors.toList()));
        // 获取每个list里的max，然后比较两个list的max，获取真正的max
        OptionalInt max1 = listStream2.map(list -> Collections.max(list)).mapToInt(Integer::intValue).max();
        System.out.println(max1);
        Stream<Stream<Integer>> streamStream = listsOfStrings.stream().map(nums -> nums.stream().map(Integer::parseInt));
        Stream<OptionalInt> optionalIntStream = streamStream.map(stream -> stream.mapToInt(Integer::intValue).max());
        Optional<OptionalInt> max2 = optionalIntStream.max((n1, n2) -> n1.getAsInt() - n2.getAsInt());
        System.out.println(max2);

        System.out.println("**** 使用flatMap *****");
        Stream<Integer> integerStream = listsOfStrings.stream().flatMap(nums -> nums.stream().map(Integer::parseInt).collect(Collectors.toList()).stream());
        System.out.println(integerStream);
        integerStream.forEach(System.out::println);
        Stream<Integer> integerStream2 = listsOfStrings.stream().flatMap(nums -> nums.stream().map(Integer::parseInt).collect(Collectors.toList()).stream());
        // 2 lists is flapped to 1 list, so just call max to get the MAX
        OptionalInt max = integerStream2.mapToInt(Integer::intValue).max();
        System.out.println(max);
    }

    @Test
    public void testIntermediateOperationChain() {
        ArrayList<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(2);
        list1.add(3);

        ArrayList<Integer> list2 = new ArrayList<>();
        list2.add(4);
        list2.add(5);
        list2.add(6);

        list1.addAll(list2.stream()
                .filter(e -> e % 2 == 0)
                .map(e -> e * 10)
                .collect(Collectors.toList()));
        System.out.println(list1);
    }

    /**
     * 排序
     * https://www.youtube.com/watch?v=DlHnbbS3bBY&list=PLmOn9nNkQxJH0qBIrtV6otI0Ep4o2q67A&index=679
     */
    @Test
    public void testSorted() {
        List<Integer> list = Arrays.asList(87, 2, 65, 34, 72, 12);
        list.stream().sorted().forEach(System.out::println);

        System.out.println("\n***********\n");

        List<Employee> employees = EmployeeData.getEmployees();
        employees.stream().sorted(Comparator.comparingInt(Employee::getAge)).forEach(System.out::println);

        System.out.println("\n********* equals ***************\n");
        Stream<Employee> stream = employees.stream();
        Stream<Employee> sorted = stream.sorted(Comparator.comparingInt(Employee::getAge));
        sorted.forEach(System.out::println);
    }
}
