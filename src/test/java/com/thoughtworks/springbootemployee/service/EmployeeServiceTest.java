package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.model.EmployeeData;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class EmployeeServiceTest {
    @Test
    void should_return_all_employees_when_get_all_employees() {
//        given
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee());
        EmployeeRepository employeeRepository = mock(EmployeeRepository.class);
        given(employeeRepository.findAll()).willReturn(employeeList);
        EmployeeService employeeService = new EmployeeService(employeeRepository);
//        when
        List<Employee> employees = employeeService.getAllEmployees();
//        then
        assertIterableEquals(employeeList, employees);
    }

    @Test
    void should_return_right_employee_when_get_employee_given_employee_id() {
//        given
        Employee employee = new Employee(1, "kiki", 18, "female", 99999);
        EmployeeRepository employeeRepository = mock(EmployeeRepository.class);
        given(employeeRepository.findById(employee.getId())).willReturn(java.util.Optional.of(employee));
        EmployeeService employeeService = new EmployeeService(employeeRepository);
//        when
        Employee getEmployee = employeeService.getEmployeeByEmployeeId(employee.getId());
//        then
        assertEquals(employee, getEmployee);
    }

    @Test
    void should_return_employees_when_get_employees_given_page_and_page_size() {
//        given
        int page = 1;
        int pageSize = 2;
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee(0, "kiki", 18, "female", 99999));
        employeeList.add(new Employee(1, "kiki", 18, "female", 99999));
        EmployeeRepository employeeRepository = mock(EmployeeRepository.class);
        PageImpl<Employee> employeePage = new PageImpl<>(employeeList, PageRequest.of(page - 1, pageSize), employeeList.size());
        given(employeeRepository.findAll(PageRequest.of(page, pageSize))).willReturn(employeePage);
        EmployeeService employeeService = new EmployeeService(employeeRepository);
//        when
        Page<Employee> employees = employeeService.getEmployeeByPageAndPageSize(page, pageSize);
//        then
        //todo 判断对象
        assertIterableEquals(employeeList, employees.getContent());
    }

    @Test
    void should_return_specify_gender_employees_when_get_employees_given_gender() {
//        given
        //todo 准备数据 不使用筛选逻辑
        String gender = "male";
        List<Employee> employees = new EmployeeData().getEmployees();
        EmployeeRepository employeeRepository = mock(EmployeeRepository.class);
        List<Employee> filterEmployees = new EmployeeData().getEmloyeesByGender(gender);

        given(employeeRepository.findEmployeesByGender(gender)).willReturn(filterEmployees);
        EmployeeService employeeService = new EmployeeService(employeeRepository);
//        when
        List<Employee> maleEmployees = employeeService.getEmployeeByGender(gender);
//        then
        assertIterableEquals(filterEmployees, maleEmployees);
    }

    @Test
    void should_return_new_employee_when_add_employee_given_employee() {
//        given
        Employee employee = new Employee(1, "kiki", 18, "female", 99999);
        EmployeeRepository employeeRepository = mock(EmployeeRepository.class);
        given(employeeRepository.save(employee)).willReturn(employee);
        EmployeeService employeeService = new EmployeeService(employeeRepository);
//        when
        Employee createdEmployee = employeeService.addEmployee(employee);
//        then
        assertEquals(employee, createdEmployee);
    }

    @Test
    void should_return_modify_employee_when_update_employee_given_update_employee() {
//        given
        int employeeID = 1;
        Employee employee = new Employee(employeeID, "kiki", 18, "male", 1000);
        Employee updateEmployee = new Employee(employeeID, "kiki", 18, "female", 5000);

        EmployeeRepository employeeRepository = mock(EmployeeRepository.class);
        given(employeeRepository.save(updateEmployee)).willReturn(updateEmployee);
        given(employeeRepository.findById(employeeID)).willReturn(java.util.Optional.of(employee));
        EmployeeService employeeService = new EmployeeService(employeeRepository);
//        when
//        todo backEmployee return null
        Employee backEmployee = employeeService.updateEmployee(employeeID, updateEmployee);
//        then
        verify(employeeRepository).save(updateEmployee);
        //assertEquals(updateEmployee, backEmployee);
    }

    @Test
    void should_return_success_message_when_delete_employee_given_employeeID() {
//        given
        int employeeID = 1;
        String message = "success";
        Employee employee = new Employee(1, "green", 19, "male", 0);
        EmployeeRepository employeeRepository = mock(EmployeeRepository.class);
        given(employeeRepository.findById(employeeID)).willReturn(Optional.of(employee));

        EmployeeService employeeService = new EmployeeService(employeeRepository);
//        when
        String backMessage = employeeService.deleteEmployeeByemployeeID(employeeID);
//        then
        assertEquals(message, backMessage);
    }
}
