package com.chuwa.tutorial.t01_basic.static_learn;

import com.chuwa.tutorial.t00_common.pojos.Employee;
import com.chuwa.tutorial.t00_common.utils.EmployeeData;

import java.util.HashSet;
import java.util.Set;

/**
 * @author b1go
 * @date 6/7/22 11:04 PM
 */
public class JvmLoad {
    public static void main(String[] args) {
        Demo demo = new Demo();
    }
}

class Demo {
    // default value is 0
    private static int n1;
    private static final String s1 = "static variable";
    private static Set<Employee> employees = new HashSet<>();
    private String s2 = "non static variable";

    static {
        System.out.println("******* 1, static block is called ***** ");
        System.out.println("******* 2, check values of static variables  ***** ");
        System.out.println(n1);
        System.out.println(s1);
        System.out.println(employees);
        System.out.println("**** done ****\n");
        // static method: EmployeeData.getEmployees()
        employees.addAll(EmployeeData.getEmployees());

        // in static block, call non-static variable
//        System.out.println(s2);
    }

    public Demo() {
        System.out.println("******* 3, Constructor is called ******");
        System.out.println(n1);
        System.out.println(s1);
        employees.forEach(System.out::println);
        System.out.println("---- s2 have value now ------");
        System.out.println(s2);
        System.out.println("*** done *****");
    }
}
