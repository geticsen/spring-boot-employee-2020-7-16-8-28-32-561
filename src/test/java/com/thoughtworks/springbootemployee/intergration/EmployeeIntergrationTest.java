package com.thoughtworks.springbootemployee.intergration;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeIntergrationTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private CompanyRepository companyRepository;

    @BeforeEach
    void dataInit() {

    }

    @AfterEach
    void init() {
        employeeRepository.deleteAll();
        companyRepository.deleteAll();
    }

    @Test
    void should_get_employee_when_hit_get_employee_end_point_given_nothing() throws Exception {
//        given
        Employee employee = new Employee(8, "kiki", 80, "female", 1000);
        employeeRepository.save(employee);
//        when
        mockMvc.perform(get("/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").isNumber())
                .andExpect(jsonPath("$[0].name").value("kiki"))
                .andExpect(jsonPath("$[0].age").value(80))
                .andExpect(jsonPath("$[0].gender").value("female"))
                .andExpect(jsonPath("$[0].salary").isNumber());
//        then
    }

    @Test
    void should_return_employee_when_hit_post_employee_end_point_given_employee() throws Exception {
//        given
        Company company = new Company(1, "oocl", 2888);
        Company savedCompany = companyRepository.save(company);
        String employeeInfo = " {\n" +
                "            \"id\": 1,\n" +
                "            \"name\": \"kiki\",\n" +
                "            \"age\": 81,\n" +
                "            \"gender\": \"female\",\n" +
                "            \"salary\": 1,\n" +
                "            \"companyId\": " + savedCompany.getId() + "\n" +
                "}";
//        when
        mockMvc.perform(post("/employees")
                .contentType(MediaType.APPLICATION_JSON).content(employeeInfo))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.name").value("kiki"))
                .andExpect(jsonPath("$.age").value(81))
                .andExpect(jsonPath("$.gender").value("female"))
                .andExpect(jsonPath("$.salary").value(1))
                .andExpect(jsonPath("$.companyId").value(savedCompany.getId()));
//        then
        List<Employee> employees = employeeRepository.findAll();
        assertEquals(1, employees.size());
        assertEquals("kiki", employees.get(0).getName());
    }

    @Test
    void should_return_modified_employee_when_put_employee_given_employee() throws Exception {
//        given
        Company company = new Company(1, "oocl", 2888);
        Company savedCompany = companyRepository.save(company);
        Employee employee = new Employee(1, "green", 20, "male", 1000, savedCompany.getId());
        Employee savedEmployee = employeeRepository.save(employee);
        String employeeInfo = " {\n" +
                "            \"id\": " + savedEmployee.getId() + ",\n" +
                "            \"name\": \"kiki\",\n" +
                "            \"age\": 18,\n" +
                "            \"gender\": \"male\",\n" +
                "            \"salary\": 100,\n" +
                "            \"companyId\": " + savedCompany.getId() + "\n" +
                "}";
//        when
        mockMvc.perform(put("/employees/" + savedEmployee.getId())
                .contentType(MediaType.APPLICATION_JSON).content(employeeInfo))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.name").value("kiki"))
                .andExpect(jsonPath("$.age").value(18))
                .andExpect(jsonPath("$.gender").value("male"))
                .andExpect(jsonPath("$.salary").value(100))
                .andExpect(jsonPath("$.companyId").value(savedCompany.getId()));


    }

    @Test
    void should_return_message_when_delete_employee_given_employee_id() throws Exception {
//        given
        String message = "success";
        Company company = new Company(1, "oocl", 2888);
        Company savedCompany = companyRepository.save(company);
        Employee employee = new Employee(1, "green", 20, "male", 1000, company.getId());
        Employee savedEmployee = employeeRepository.save(employee);
//        when
        mockMvc.perform(delete("/employees/" + savedEmployee.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("success"));
    }
}
