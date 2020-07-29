package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.CompanyData;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
        assertEquals(companies.size(),getCompanies.size());

    }
}
