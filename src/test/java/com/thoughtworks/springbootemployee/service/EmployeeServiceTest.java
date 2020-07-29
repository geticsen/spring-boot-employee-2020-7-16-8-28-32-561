package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.model.EmployeeData;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

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
        assertEquals(1, employees.size());
    }

    @Test
    void should_return_right_employee_when_get_employee_given_employee_id() {
//        given
        Employee employee = new Employee(1, "kiki", 18, "female", 99999);
        EmployeeRepository employeeRepository = mock(EmployeeRepository.class);
        given(employeeRepository.getEmployeeByEmployeeId(employee.getId())).willReturn(employee);
        EmployeeService employeeService = new EmployeeService(employeeRepository);
//        when
        Employee getEmployee = employeeService.getEmployeeByEmployeeId(employee.getId());
//        then
        assertEquals(employee.getId(), getEmployee.getId());
        assertEquals(employee.getAge(), getEmployee.getAge());
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
        given(employeeRepository.getEmployeeByPageAndPageSize(page, pageSize)).willReturn(employeeList);
        EmployeeService employeeService = new EmployeeService(employeeRepository);
//        when
        List<Employee> employees = employeeService.getEmployeeByPageAndPageSize(page, pageSize);
//        then
        assertEquals(employeeList.size(), employees.size());
        assertEquals(employeeList.get(0).getId(), employees.get(0).getId());
    }

    @Test
    void should_return_specify_gender_employees_when_get_employees_given_gender() {
//        given
        String gender = "male";
        List<Employee> employees = new EmployeeData().getEmployees();
        EmployeeRepository employeeRepository = mock(EmployeeRepository.class);
        List<Employee> filterEmployees = employees.stream().filter(employee ->
                employee.getGender().equals(gender))
                .collect(Collectors.toList());
        given(employeeRepository.getEmployeeByGender(gender)).willReturn(filterEmployees);
        EmployeeService employeeService = new EmployeeService(employeeRepository);
//        when
        List<Employee> maleEmployees = employeeService.getEmployeeByGender(gender);
//        then
        assertEquals(filterEmployees.size(), maleEmployees.size());
        Random random = new Random();
        int randInt = random.nextInt(filterEmployees.size());
        assertEquals(gender, maleEmployees.get(randInt).getGender());
    }

    @Test
    void should_return_new_employee_when_add_employee_given_employee() {
//        given
        Employee employee = new Employee(1, "kiki", 18, "female", 99999);
        EmployeeRepository employeeRepository = mock(EmployeeRepository.class);
        given(employeeRepository.addEmployee(employee)).willReturn(employee);
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
        Employee updateEmployee = new Employee(employeeID, "kiki", 18, "male", 1000);
        EmployeeRepository employeeRepository = mock(EmployeeRepository.class);
        given(employeeRepository.updateEmployee(employeeID, updateEmployee)).willReturn(updateEmployee);
        EmployeeService employeeService = new EmployeeService(employeeRepository);
//        when
        Employee backEmployee = employeeService.updateEmployee(employeeID, updateEmployee);
//        then
        assertEquals(updateEmployee.getGender(), backEmployee.getGender());
    }

    @Test
    void should_return_success_message_when_delete_employee_given_employeeID() {
//        given
        int employeeID = 1;
        String message = "success";
        EmployeeRepository employeeRepository = mock(EmployeeRepository.class);
        given(employeeRepository.deleteEmployeeByemployeeID(employeeID)).willReturn(message);
        EmployeeService employeeService = new EmployeeService(employeeRepository);
//        when
        String backMessage = employeeService.deleteEmployeeByemployeeID(employeeID);
//        then
        assertEquals(message,backMessage);
    }
}
