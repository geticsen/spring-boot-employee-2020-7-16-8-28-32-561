package com.thoughtworks.springbootemployee.intergration;

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

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

    @AfterEach
    void init() {
        employeeRepository.deleteAll();
        companyRepository.deleteAll();
    }

    @Test
    void should_get_employee_when_hit_get_employee_end_point_given_nothing() throws Exception {
//        given
        Company company = new Company(1, "oocl", 2888);
        Company savedCompany = companyRepository.save(company);
        Employee employee = new Employee(8, "kiki", 80, "female", 1000);
        employee.setCompanyId(company.getId());
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
}
