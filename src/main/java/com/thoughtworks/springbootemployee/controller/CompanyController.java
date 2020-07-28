package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    @GetMapping
    public List<Company> getAllCompany(@RequestParam(name = "page" ,required = false) Integer page,@RequestParam(name = "pageSize",required = false) Integer pageSize){
        List<Company> companies=new ArrayList<>();
        companies.add(new Company(1,"ali",1));
        companies.add(new Company(2,"tx",2));
        companies.add(new Company(3,"hw",3));
        companies.add(new Company(4,"zj",3));
        companies.add(new Company(5,"oocl",3));
        companies.add(new Company(6,"yy",3));
        companies.add(new Company(7,"meituan",3));

        if(page!=null&&pageSize!=null){
            return companies.subList(--page,--pageSize);
        }

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

        assert result != null;
        return result.getEmployees();
    }
}
