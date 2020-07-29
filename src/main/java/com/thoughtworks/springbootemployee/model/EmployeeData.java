package com.thoughtworks.springbootemployee.model;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class EmployeeData {
    public static final String MALE = "male";
    public static final String FEMALE = "female";
    private final List<Employee> employees=new ArrayList<>();

    public EmployeeData() {
        employees.add(new Employee(1,"lzh",18, MALE,5000));
        employees.add(new Employee(2,"green",19,MALE,6000));
        employees.add(new Employee(3,"alex",12, FEMALE,7000));
        employees.add(new Employee(4,"chris",17,MALE,8000));
        employees.add(new Employee(5,"york",80,MALE,9000));
        employees.add(new Employee(6,"karen",19,FEMALE,9000));
        employees.add(new Employee(7,"tony",80,MALE,9000));
    }

    public List<Employee> getEmployees() {
        return employees;
    }
}
