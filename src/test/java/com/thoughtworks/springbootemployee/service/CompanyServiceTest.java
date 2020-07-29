package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.CompanyData;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.model.EmployeeData;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;


public class CompanyServiceTest {
    @Test
    void should_get_all_companies_when_get_all_given_no_paremeter() {
//        given
        List<Company> companies = new CompanyData().getCompanies();
//        when
        CompanyRepository companyRepository = mock(CompanyRepository.class);
        given(companyRepository.getAll()).willReturn(companies);
        CompanyService companyService = new CompanyService(companyRepository);
        List<Company> getCompanies = companyService.getAll();
//        then
        assertEquals(companies.size(), getCompanies.size());

    }

    @Test
    void should_return_specify_company_when_get_company_given_company_id() {
        //        given
        int companyId = 1;
        Company filterCompany = new CompanyData().getCompanies().stream()
                .filter(company -> company.getId() == companyId).findFirst().orElse(null);
//        when
        CompanyRepository companyRepository = mock(CompanyRepository.class);
        given(companyRepository.getCompanyByCompanyId(companyId)).willReturn(filterCompany);
        CompanyService companyService = new CompanyService(companyRepository);
        Company getCompany = companyService.getCompanyByCompanyId(companyId);
//        then
        assert filterCompany != null;
        assertEquals(filterCompany.getId(), getCompany.getId());
    }
    @Test
    void should_get_all_employees_when_get_employees_given_company_id() {
//        given
        int companyId = 1;
        List<Employee> employees = new EmployeeData().getEmployees();
        List<Company> companies = new CompanyData().getCompanies();
        Objects.requireNonNull(companies.stream().filter(company -> company.getId() == companyId).findFirst().orElse(null)).setEmployees(employees);
//        when
        CompanyRepository companyRepository = mock(CompanyRepository.class);
        given(companyRepository.getEmployeesByCompanyId(companyId)).willReturn(employees);
        CompanyService companyService = new CompanyService(companyRepository);
        List<Employee> getEmployees = companyService.getEmployeesByCompanyId(companyId);
//        then
        assertEquals(employees.size(),getEmployees.size());
    }

    @Test
    void should_return_companies_when_get_companies_given_page_and_page_size() {
//        given
        int page = 1;
        int pageSize = 2;
        List<Company> companies = new CompanyData().getCompanies();
        List<Company> subCompanies = companies.subList((page - 1) * pageSize, page * pageSize);
        CompanyRepository companyRepository = mock(CompanyRepository.class);
        given(companyRepository.getCompaniesByPageAndPageSize(page,pageSize)).willReturn(subCompanies);
        CompanyService companyService = new CompanyService(companyRepository);
//        when
        List<Company> getCompanies = companyService.getCompaniesByPageAndPageSize(page,pageSize);
//        then
        assertEquals(subCompanies.size(), getCompanies.size());
        assertEquals(subCompanies.get(0).getId(), getCompanies.get(0).getId());
    }

    @Test
    void should_return_company_when_add_company_given_company() {
//        given
        Company company = new Company(1,"oocl",1000);
        CompanyRepository companyRepository = mock(CompanyRepository.class);
        given(companyRepository.addCompany(company)).willReturn(company);
        CompanyService companyService = new CompanyService(companyRepository);
//        when
        Company createdCompany = companyService.addCompany(company);
//        then
        assertEquals(company.getId(),createdCompany.getId());
    }

    @Test
    void should_return_updated_company_when_update_company_given_company_id_and_company_info() {
//        given
        int companyId = 1;
        Company company = new Company(companyId,"oocl",1000);
        Company companyInfo = new Company(companyId,"ali",1);
        CompanyRepository companyRepository = mock(CompanyRepository.class);
        given(companyRepository.updateCompany(companyId,companyInfo)).willReturn(companyInfo);
        CompanyService companyService = new CompanyService(companyRepository);
//        when
        Company updatedCompany = companyService.updateCompany(companyId,companyInfo);
//        then
        assertEquals(companyInfo.getCompanyName(),updatedCompany.getCompanyName());
        assertEquals(companyInfo.getEmployeesNumber(),updatedCompany.getEmployeesNumber());
    }

    @Test
    void should_return_success_message_when_delete_company_given_company_id() {
//        given
        int companyID = 1;
        String message = "success";
        CompanyRepository companyRepository = mock(CompanyRepository.class);
        given(companyRepository.deleteCompanyByCompanyID(companyID)).willReturn(message);
        CompanyService companyService = new CompanyService(companyRepository);
//        when
        String backMessage = companyService.deleteCompanyByCompanyID(companyID);
//        then
        assertEquals(message,backMessage);
    }
}
