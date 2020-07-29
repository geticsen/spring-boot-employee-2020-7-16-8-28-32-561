package com.thoughtworks.springbootemployee.repository;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;

import java.util.List;

public class CompanyRepository {
    public List<Company> getAll() {
        return null;
    }

    public Company getCompanyByCompanyId(int companyId) {
        return null;
    }
    public List<Employee> getEmployeesByCompanyId(int companyId){
        return null;
    }

    public List<Company> getCompaniesByPageAndPageSize(int page, int pageSize) {
        return null;
    }
}
