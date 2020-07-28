package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.CompanyData;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.model.EmployeeData;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    private static final CompanyData companyData = new CompanyData();
    private static final EmployeeData employeeData = new EmployeeData();
    private static final String SUCCESS_MESSAGE = "success";
    private static final String FAIL_MESSAGE = "fail";

    @GetMapping
    public List<Company> getAllCompany(@RequestParam(name = "page", required = false) Integer page,
                                       @RequestParam(name = "pageSize", required = false) Integer pageSize) {
        if (page != null && pageSize != null) {
            return companyData.getCompanies().subList(--page, --pageSize);
        }
        return companyData.getCompanies();
    }

    @GetMapping("/{companyId}/employees")
    public List<Employee> getEmplyeesByCompanyId(@PathVariable int companyId) {
        initEmplyeesData(companyId);
        Company result = companyData.getCompanies().stream().filter(company -> {
            return company.getId() == companyId;
        }).findFirst().orElse(null);
        assert result != null;
        return result.getEmployees();
    }

    @GetMapping("/{companyId}")
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
    public String addCompany(@RequestBody Company company) {
        if (company != null) {
            companyData.getCompanies().add(company);
        }
        if (companyData.getCompanies().contains(company)) {
            return SUCCESS_MESSAGE;
        }
        return FAIL_MESSAGE;
    }

    @PutMapping("/{companyId}")
    public String modifyCompanyByCompanyId(@PathVariable int companyId, @RequestBody(required = false) Company company) {
        if (company != null) {
            Company modifyCompany = companyData.getCompanies().stream().filter(findCompany -> {
                return findCompany.getId() == companyId;
            }).findFirst().orElse(null);
            if (modifyCompany != null) {
                companyData.getCompanies().set(companyData.getCompanies().indexOf(modifyCompany), company);
                return SUCCESS_MESSAGE;
            }
        }
        return FAIL_MESSAGE;
    }
    @DeleteMapping("/{companyId}")
    public String deleteCompanyByCompanyId(@PathVariable int companyId){
        Company deleteCompany = companyData.getCompanies().stream().filter(company -> {
            return company.getId() == companyId;
        }).findFirst().orElse(null);

        if(deleteCompany!=null){
            companyData.getCompanies().remove(deleteCompany);
            return SUCCESS_MESSAGE;
        }
        return FAIL_MESSAGE;
    }

}
