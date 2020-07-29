package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.message.ResponseMessage;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.model.EmployeeData;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    private static final EmployeeData employeeData = new EmployeeData();

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> getAllEmployee(@RequestParam(name = "page", required = false) Integer page,
                                         @RequestParam(name = "pageSize", required = false) Integer pageSize,
                                         @RequestParam(name = "gender", required = false) String gender) {
        List<Employee> employees = new ArrayList<>();
        if (page != null && pageSize != null) {
            employees = employeeService.getEmployeeByPageAndPageSize(page, pageSize).getContent();
        } else if (gender != null) {
            employees = employeeService.getEmployeeByGender(gender);
        } else {
            employees = employeeService.getAllEmployees();
        }
        return employees;
    }

    @GetMapping("/{employeeId}")
    @ResponseStatus(HttpStatus.OK)
    public Employee getEmployeeByEmployeeId(@PathVariable int employeeId) {
        return employeeService.getEmployeeByEmployeeId(employeeId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String addEmployee(@RequestBody Employee employee) {
        if (employee != null) {
            employeeService.addEmployee(employee);
            return ResponseMessage.SUCCESS_MESSAGE;
        }
        return ResponseMessage.FAIL_MESSAGE;
    }

    @PutMapping("/{employeeId}")
    @ResponseStatus(HttpStatus.OK)
    public String modifyEmployeeByEmployeeId(@PathVariable int employeeId, @RequestBody Employee modifyEmployee) {
        if (modifyEmployee != null) {
            employeeService.updateEmployee(employeeId,modifyEmployee);
            return ResponseMessage.SUCCESS_MESSAGE;
        }
        return ResponseMessage.FAIL_MESSAGE;
    }

    @DeleteMapping("/{employeeId}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteEmployeeByEmployeeId(@PathVariable int employeeId) {
        return employeeService.deleteEmployeeByemployeeID(employeeId);
    }
}
