package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    @GetMapping
    public List<Company> getAllCompany(){
        List<Company> companies=new ArrayList<>();
        companies.add(new Company(1,"ali",1));
        companies.add(new Company(2,"tx",2));
        companies.add(new Company(3,"hw",3));
        return companies;
    }

    @GetMapping("/{companyId}/employees")
    public List<Employee> getEmplyeesByCompanyId(@PathVariable int companyId){
        List<Employee> employees=new ArrayList<>();
        employees.add(new Employee(1,"lzh",18,"男",5000));
        employees.add(new Employee(2,"green",19,"男",6000));
        employees.add(new Employee(3,"alex",12,"女",7000));

        Company ali = new Company(1, "ali", 20);
        Company tx = new Company(2, "tx", 20);
        ali.setEmployees(employees);

        List<Company> companies=new ArrayList<>();
        companies.add(ali);
        companies.add(tx);

        Company result = companies.stream().filter(company -> {
            return company.getId() == companyId;
        }).findFirst().orElse(null);

        return result.getEmployees();
    }

}
