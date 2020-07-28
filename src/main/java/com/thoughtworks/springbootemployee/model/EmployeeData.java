package com.thoughtworks.springbootemployee.model;

import java.util.ArrayList;
import java.util.List;

public class EmployeeData {
    private final List<Employee> employees=new ArrayList<>();

    public EmployeeData() {
        employees.add(new Employee(1,"lzh",18,"男",5000));
        employees.add(new Employee(2,"green",19,"男",6000));
        employees.add(new Employee(3,"alex",12,"女",7000));
        employees.add(new Employee(4,"chris",17,"男",8000));
        employees.add(new Employee(5,"york",80,"男",9000));
        employees.add(new Employee(6,"karen",19,"女",9000));
        employees.add(new Employee(7,"tony",80,"男",9000));
    }

    public List<Employee> getEmployees() {
        return employees;
    }
}
