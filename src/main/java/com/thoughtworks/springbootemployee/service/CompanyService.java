package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;

import java.util.List;

public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public List<Company> getAll() {
        return companyRepository.getAll();
    }

    public Company getCompanyByCompanyId(int companyId) {
        return companyRepository.getCompanyByCompanyId(companyId);
    }

    public List<Employee> getEmployeesByCompanyId(int companyId) {
        return companyRepository.getEmployeesByCompanyId(companyId);
    }

    public List<Company> getCompaniesByPageAndPageSize(int page, int pageSize) {
        return companyRepository.getCompaniesByPageAndPageSize(page,pageSize);
    }

    public Company addCompany(Company company) {
        return companyRepository.addCompany(company);
    }
}
