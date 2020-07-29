package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.message.ResponseMessage;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.model.EmployeeData;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private final EmployeeRepository employeeRepository;


    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;

    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeByEmployeeId(Integer id) {
        return employeeRepository.findById(id).orElse(null);
    }

    public Page<Employee> getEmployeeByPageAndPageSize(int page, int pageSize) {
        return this.employeeRepository.findAll(PageRequest.of(page,pageSize));
    }

    public List<Employee> getEmployeeByGender(String gender) {
        return employeeRepository.findEmployeesByGender(gender);
    }

    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(int employeeID, Employee updateEmployee) {
        updateEmployee.setId(employeeID);
        return employeeRepository.save(updateEmployee);
    }

    public String deleteEmployeeByemployeeID(Integer employeeID) {
        employeeRepository.deleteById(employeeID);
        Optional<Employee> employee = employeeRepository.findById(employeeID);
        if (employee.isPresent()){
            return ResponseMessage.FAIL_MESSAGE;
        }else{
            return ResponseMessage.SUCCESS_MESSAGE;
        }
    }
}
