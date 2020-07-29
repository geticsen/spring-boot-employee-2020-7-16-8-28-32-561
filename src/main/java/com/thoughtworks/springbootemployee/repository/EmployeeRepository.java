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

    public List<Employee> getEmployeeByPageAndPageSize(int page, int pageSize) {
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee(0, "kiki", 18, "female", 99999));
        employeeList.add(new Employee(1, "kiki", 18, "female", 99999));
        int start = (page-1)*pageSize;
        int end = page*pageSize;
        return  employeeList.subList(start,end);
    }
}
