package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.employee;
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
    public List<employee> getEmplyeesByCompanyId(@PathVariable int companyId){

        return null;
    }

}
