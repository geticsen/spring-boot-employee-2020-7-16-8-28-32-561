package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.message.ResponseMessage;
import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.CompanyData;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.model.EmployeeData;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    private static final CompanyData companyData = new CompanyData();
    private static final EmployeeData employeeData = new EmployeeData();

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Company> getAllCompany(@RequestParam(name = "page", required = false) Integer page,
                                       @RequestParam(name = "pageSize", required = false) Integer pageSize) {
        if (page != null && pageSize != null) {
            int start = --page * pageSize;
            int end = ++page * pageSize;
            return companyData.getCompanies().subList(start, end);
        }
        return companyData.getCompanies();
    }

    @GetMapping("/{companyId}/employees")
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> getEmplyeesByCompanyId(@PathVariable int companyId) {
        initEmplyeesData(companyId);
        Company result = companyData.getCompanies().stream().filter(company -> {
            return company.getId() == companyId;
        }).findFirst().orElse(null);
        assert result != null;
        return result.getEmployees();
    }

    @GetMapping("/{companyId}")
    @ResponseStatus(HttpStatus.OK)
    public Company getCompanyByCompanyId(@PathVariable int companyId) {
        return companyData.getCompanies().stream().filter(company -> {
            return company.getId() == companyId;
        }).findFirst().orElse(null);
    }

    public void initEmplyeesData(int companyId) {
        Company result = companyData.getCompanies().stream().filter(company -> {
            return company.getId() == companyId;
        }).findFirst().orElse(null);
        assert result != null;
        result.setEmployees(employeeData.getEmployees().subList(0, 3));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String addCompany(@RequestBody Company company) {
        if (company != null) {
            companyData.getCompanies().add(company);
        }
        if (companyData.getCompanies().contains(company)) {
            return ResponseMessage.SUCCESS_MESSAGE;
        }
        return ResponseMessage.FAIL_MESSAGE;
    }

    @PutMapping("/{companyId}")
    @ResponseStatus(HttpStatus.OK)
    public String modifyCompanyByCompanyId(@PathVariable int companyId, @RequestBody(required = false) Company company) {
        if (company != null) {
            Company modifyCompany = companyData.getCompanies().stream().filter(findCompany -> {
                return findCompany.getId() == companyId;
            }).findFirst().orElse(null);
            if (modifyCompany != null) {
                companyData.getCompanies().set(companyData.getCompanies().indexOf(modifyCompany), company);
                return ResponseMessage.SUCCESS_MESSAGE;
            }
        }
        return ResponseMessage.FAIL_MESSAGE;
    }

    @DeleteMapping("/{companyId}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteCompanyByCompanyId(@PathVariable int companyId) {
        Company deleteCompany = companyData.getCompanies().stream().filter(company -> {
            return company.getId() == companyId;
        }).findFirst().orElse(null);

        if (deleteCompany != null) {
            deleteCompany.getEmployees().forEach(employee -> {
                employeeData.getEmployees().remove(employee);
            });
            companyData.getCompanies().remove(deleteCompany);
            return ResponseMessage.SUCCESS_MESSAGE;
        }
        return ResponseMessage.FAIL_MESSAGE;
    }

}
