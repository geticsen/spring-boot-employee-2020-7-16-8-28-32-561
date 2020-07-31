package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.CompanyData;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.model.EmployeeData;
import com.thoughtworks.springbootemployee.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")


public class CompanyController {
    @Autowired
    private CompanyService companyService;
    private final CompanyData companyData = new CompanyData();
    private EmployeeData employeeData = new EmployeeData();

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Company> getAllCompany(@RequestParam(name = "page", required = false) Integer page,
                                       @RequestParam(name = "pageSize", required = false) Integer pageSize) {
        if (page != null && pageSize != null) {
            return companyService.getCompaniesByPageAndPageSize(page, pageSize).getContent();
        }
        return companyService.getAll();
    }

    @GetMapping("/{companyId}/employees")
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> getEmplyeesByCompanyId(@PathVariable Integer companyId) {
        return companyService.getEmployeesByCompanyId(companyId);
    }

    @GetMapping("/{companyId}")
    @ResponseStatus(HttpStatus.OK)
    public Company getCompanyByCompanyId(@PathVariable Integer companyId) {
        return companyService.getCompanyByCompanyId(companyId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Company addCompany(@RequestBody Company company) {
        if (company != null) {
            return companyService.addCompany(company);
        } else {
            return null;
        }
    }

    @PutMapping("/{companyId}")
    @ResponseStatus(HttpStatus.OK)
    public Company modifyCompanyByCompanyId(@PathVariable Integer companyId, @RequestBody(required = false) Company company) {
        if (company != null) {
            return companyService.updateCompany(companyId, company);
        } else {
            return null;
        }
    }

    @DeleteMapping("/{companyId}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteCompanyByCompanyId(@PathVariable Integer companyId) {
        return companyService.deleteCompanyByCompanyID(companyId);
    }

}
