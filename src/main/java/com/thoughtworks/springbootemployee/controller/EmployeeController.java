package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.model.EmployeeData;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private static final EmployeeData employeeData = new EmployeeData();

    @GetMapping
    public List<Employee> getAllEmployee(@RequestParam(name = "page", required = false) Integer page,
                                         @RequestParam(name = "pageSize", required = false) Integer pageSize,
                                         @RequestParam(name = "gender", required = false) String gender) {
        List<Employee> employees = new ArrayList<>(employeeData.getEmployees());
        if (page != null && pageSize != null) {
            employees = employees.subList(--page, --pageSize);
        }
        System.out.println(gender);
        if (gender != null) {
            employees = employees.stream().filter(employee -> {
                return employee.getGender().equals(gender);
            }).collect(Collectors.toList());
        }
        return employees;
    }

    @GetMapping("/{employeeId}")
    public Employee getEmployeeByEmployeeId(@PathVariable int employeeId) {
        return employeeData.getEmployees().stream().filter(employee -> {
            return employeeId == employee.getId();
        }).
                findFirst().orElse(null);
    }

    @PostMapping
    public String addEmployee(@RequestBody Employee employee){
        if(employee!=null){
            employeeData.getEmployees().add(employee);
            return "success";
        }
        return "fail";
    }
}
