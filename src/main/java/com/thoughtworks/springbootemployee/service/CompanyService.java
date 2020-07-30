package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.message.ResponseMessage;
import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CompanyService {

    @Autowired
    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public List<Company> getAll() {
        return companyRepository.findAll();
    }

    public Company getCompanyByCompanyId(Integer companyId) {
        return companyRepository.findById(companyId).orElse(null);
    }

    public List<Employee> getEmployeesByCompanyId(int companyId) {
        return Objects.requireNonNull(companyRepository.findById(companyId).orElse(null)).getEmployees();
    }

    public Page<Company> getCompaniesByPageAndPageSize(int page, int pageSize) {
        return companyRepository.findAll(PageRequest.of(page - 1, pageSize));
    }

    public Company addCompany(Company company) {
        return companyRepository.save(company);
    }

    public Company updateCompany(int companyId, Company companyInfo) {
        companyInfo.setId(companyId);
        return companyRepository.save(companyInfo);
    }

    public String deleteCompanyByCompanyID(Integer companyID) {
        companyRepository.deleteById(companyID);
        Optional<Company> company = companyRepository.findById(companyID);
        if (company.isPresent()){
            return ResponseMessage.FAIL_MESSAGE;
        }else{
            return ResponseMessage.SUCCESS_MESSAGE;
        }
    }
}
