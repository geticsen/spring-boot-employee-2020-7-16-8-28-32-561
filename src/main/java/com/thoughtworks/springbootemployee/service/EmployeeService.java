package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;

import java.util.List;

public class EmployeeService {
    public EmployeeService(EmployeeRepository employeeRepository) {
    }

    public List<Employee> getAllEmployees() {
        return null;
    }
}
