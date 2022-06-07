package com.chuwa.tutorial.java8.utils;


import com.chuwa.tutorial.java8.pojos.Employee;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yanan Lyu
 * @date 7/7/21 12:46 PM
 */
public class EmployeeData {

    public static List<Employee> getEmployees() {
        List<Employee> list = new ArrayList<>();

        list.add(new Employee(1001, "Yun Ma", 50, 30000.00));
        list.add(new Employee(1002, "Huateng Ma", 49, 22222.22));
        list.add(new Employee(1004, "Jun Lei", 43, 12234.12));
        list.add(new Employee(1005, "Bill Gates", 65, 999999));
        list.add(new Employee(1003, "Yanhong Li", 30, 123123));
        list.add(new Employee(1007, "Zhengfei Ren", 78, 66666));
        list.add(new Employee(1006, "Mark Elliot Zuckerberg", 29, 88888));

        return list;
    }
}
