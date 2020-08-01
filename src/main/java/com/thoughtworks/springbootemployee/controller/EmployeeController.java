package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.dto.EmployeeRequest;
import com.thoughtworks.springbootemployee.dto.EmployeeResponse;
import com.thoughtworks.springbootemployee.exception.NoSuchDataException;
import com.thoughtworks.springbootemployee.mapper.EmployeeMapper;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.model.EmployeeData;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
            employees = employeeService.getEmployeeByPageAndPageSize(page-1, pageSize).getContent();
        } else if (gender != null) {
            employees = employeeService.getEmployeeByGender(gender);
        } else {
            employees = employeeService.getAllEmployees();
        }
        return employees;
    }

    @GetMapping("/{employeeId}")
    @ResponseStatus(HttpStatus.OK)
    public Employee getEmployeeByEmployeeId(@PathVariable Integer employeeId) {
        return employeeService.getEmployeeByEmployeeId(employeeId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeResponse addEmployee(@RequestBody EmployeeRequest employeeRequest) {
        if (employeeRequest != null) {
            Employee employee = new EmployeeMapper().convertEmployeeRequestToEmployee(employeeRequest);
            return employeeService.addEmployee(employee);
        }
        return null;
    }

    @PutMapping("/{employeeId}")
    @ResponseStatus(HttpStatus.OK)
    public Employee modifyEmployeeByEmployeeId(@PathVariable Integer employeeId, @RequestBody Employee modifyEmployee) throws NoSuchDataException {
        if (modifyEmployee != null) {
            return employeeService.updateEmployee(employeeId, modifyEmployee);
        }
        return null;
    }

    @DeleteMapping("/{employeeId}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteEmployeeByEmployeeId(@PathVariable Integer employeeId) {
        return employeeService.deleteEmployeeByemployeeID(employeeId);
    }
}
