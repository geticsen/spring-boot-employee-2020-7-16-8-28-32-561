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

    public Employee getEmployeeByEmployeeId(int id) {
        return new Employee(1,"kiki",18,"female",99999);
    }

    public Object getEmployeeByPageAndPageSize(int page, int pageSize) {
        return null;
    }
}
