package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class EmployeeServiceTest {
    @Test
    void should_return_all_employees_when_get_all_employees() {
//        given
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee());
        EmployeeRepository employeeRepository = mock(EmployeeRepository.class);
        given(employeeRepository.getAllEmploees()).willReturn(employeeList);
        EmployeeService employeeService = new EmployeeService(employeeRepository);
//        when
        List<Employee> employees = employeeService.getAllEmployees();
//        then
        assertEquals(1,employees.size());
    }

    @Test
    void should_return_right_employee_when_get_employee_given_employee_id() {
//        given
        Employee employee =new Employee(1,"kiki",18,"female",99999);
        EmployeeRepository employeeRepository = mock(EmployeeRepository.class);
        given(employeeRepository.getEmployeeByEmployeeId(employee.getId())).willReturn(employee);
        EmployeeService employeeService = new EmployeeService(employeeRepository);
//        when
        Employee getEmployee = employeeService.getEmployeeByEmployeeId(employee.getId());
//        then
        assertEquals(employee.getId(),getEmployee.getId());
        assertEquals(employee.getAge(),getEmployee.getAge());
    }
}
