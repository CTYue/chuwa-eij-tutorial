package com.chuwa.tutorial.t01_basic;

import com.chuwa.tutorial.t00_common.pojos.Employee;
import com.chuwa.tutorial.t00_common.utils.EmployeeData;

import java.util.HashSet;
import java.util.Set;

/**
 * @author b1go
 * @date 6/7/22 10:26 PM
 */
public class EmployeeRepository {
    private static Set<Employee> employees = new HashSet<>();

    static {
        // static method: EmployeeData.getEmployees()
        employees.addAll(EmployeeData.getEmployees());
    }

    public Set<Employee> getEmployees() {
        return employees;
    }
}
