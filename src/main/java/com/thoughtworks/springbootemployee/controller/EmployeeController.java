package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.model.EmployeeData;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private static final EmployeeData employeeData = new EmployeeData();

    @GetMapping
    public List<Employee> getAllEmployee(@RequestParam(name = "page", required = false) Integer page, @RequestParam(name = "pageSize", required = false) Integer pageSize){
        if (page != null && pageSize != null) {
            return employeeData.getEmployees().subList(--page, --pageSize);
        }
        return employeeData.getEmployees();
    }

    @GetMapping("/{employeeId}")
    public Employee getEmployeeByEmployeeId(@PathVariable int employeeId){
        return employeeData.getEmployees().stream().filter(employee -> {return employeeId==employee.getId();}).
                findFirst().orElse(null);
    }




}
