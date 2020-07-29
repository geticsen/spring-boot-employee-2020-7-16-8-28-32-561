package com.thoughtworks.springbootemployee.repository;

import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class EmployeeRepository {
    public List<Employee> getAllEmploees() {
        List<Employee> employees =new ArrayList<>();
        employees.add(new Employee());
        return employees;
    }
}
