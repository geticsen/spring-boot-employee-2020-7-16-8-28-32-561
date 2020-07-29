package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.model.EmployeeData;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;


    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;

    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.getAllEmploees();
    }

    public Employee getEmployeeByEmployeeId(int id) {
        return employeeRepository.getEmployeeByEmployeeId(id);
    }

    public List<Employee> getEmployeeByPageAndPageSize(int page, int pageSize) {
        return this.employeeRepository.getEmployeeByPageAndPageSize(page,pageSize);
    }

    public List<Employee> getEmployeeByGender(String gender) {
        return employeeRepository.getEmployeeByGender(gender);
    }

    public Employee addEmployee(Employee employee) {
        return employeeRepository.addEmployee(employee);
    }

    public Employee updateEmployee(int employeeID, Employee updateEmployee) {
        return employeeRepository.updateEmployee(employeeID,updateEmployee);
    }

    public String deleteEmployeeByemployeeID(int employeeID) {
        return null;
    }
}
