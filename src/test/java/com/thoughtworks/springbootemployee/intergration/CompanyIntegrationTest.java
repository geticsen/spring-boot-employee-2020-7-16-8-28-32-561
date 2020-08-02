package com.thoughtworks.springbootemployee.intergration;

import com.thoughtworks.springbootemployee.message.ResponseMessage;
import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CompanyIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @AfterEach
    void deleteData() {
        companyRepository.deleteAll();
        employeeRepository.deleteAll();
    }

    @Test
    void should_return_companies_when_get_companies_given_none() throws Exception {
//        given
        Company company = new Company(1, "oocl", 2888);
        Company savedCompany = companyRepository.save(company);

//        when then
        mockMvc.perform(get("/companies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").isNumber())
                .andExpect(jsonPath("$[0].companyName").value("oocl"))
                .andExpect(jsonPath("$[0].employeesNumber").value(2888))
                .andExpect(jsonPath("$[0].employees").value(new ArrayList<Employee>()));
    }

    @Test
    void should_return_company_when_get_companies_given_company_id() throws Exception {
//        given
        Company company = new Company(1, "oocl", 2888);
        Company savedCompany = companyRepository.save(company);

//        when then
        mockMvc.perform(get("/companies/" + savedCompany.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.companyName").value("oocl"))
                .andExpect(jsonPath("$.employeesNumber").value(2888))
                .andExpect(jsonPath("$.employees").value(new ArrayList<Employee>()));
    }

    @Test
    void should_return_company_employees_when_get_employees_by_company_id_given_company_id() throws Exception {
//        given
        Company company = new Company(1, "oocl", 2888);
        Company savedCompany = companyRepository.save(company);
        Employee employee = new Employee(1, "green", 20, "male", 1000, savedCompany.getId());
        Employee savedEmployee = employeeRepository.save(employee);
//        when then
        mockMvc.perform(get("/companies/" + savedCompany.getId() + "/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").value(savedEmployee.getId()))
                .andExpect(jsonPath("$[0].name").value(savedEmployee.getName()));
    }

    @Test
    void should_return_companies_when_get_companies_given_page_and_page_size() throws Exception {
//        given
        int page = 1;
        int pageSize = 1;
        Company company1 = new Company(0, "oocl", 2888);
        Company company2 = new Company(1, "ali", 0);
        Company savedCompany1 = companyRepository.save(company1);
        Company savedCompany2 = companyRepository.save(company2);
//        when then
        mockMvc.perform(get("/companies?page=" + page + "&pageSize=" + pageSize))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(pageSize)))
                .andExpect(jsonPath("$[0].id").value(savedCompany1.getId()));
    }

    @Test
    void should_return_company_when_post_company_given_company() throws Exception {
//        given
        String companyString = "{\n" +
                "\"id\": 2,\n" +
                "\"Employees\": null,\n" +
                "\"companyName\": \"dz\",\n" +
                "\"employeesNumber\": 1000\n" +
                "}";
//        when then
        mockMvc.perform(post("/companies")
                .contentType(MediaType.APPLICATION_JSON)
                .content(companyString))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.companyName").value("dz"))
                .andExpect(jsonPath("$.employeesNumber").value(1000));
    }

    @Test
    void should_return_mmodify_company_when_put_company_info_given_company_info() throws Exception {
        //        given
        Company company = new Company(1, "ali", 30);
        Company savedCompany = companyRepository.save(company);
        String companyInfo = "{\n" +
                "\"id\": " + savedCompany.getId() + ",\n" +
                "\"companyName\": \"dz\",\n" +
                "\"employeesNumber\": 1000\n" +
                "}";
//        when then
        mockMvc.perform(put("/companies/" + savedCompany.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(companyInfo))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.companyName").value("dz"))
                .andExpect(jsonPath("$.employeesNumber").value(1000));
    }

    @Test
    void should_return_message_when_delete_company_given_company_id() throws Exception {
//        given
        Company company = new Company(1, "ali", 10);
        Company savedCompany = companyRepository.save(company);
        String message = ResponseMessage.SUCCESS_MESSAGE;
//        when then
        mockMvc.perform(delete("/companies/" + savedCompany.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(message));
    }
}
