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
import org.springframework.util.MultiValueMap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Test
    void should_return_employees_when_get_employees_given_page_and_page_size() throws Exception {
//        given
        int page = 1;
        int pageSize = 2;
        String url = "/employees?page=" + page + "&pageSize=" + pageSize;
        Employee employee1 = new Employee("mht", 50, "male", 100000);
        Employee employee2 = new Employee("mht", 50, "male", 100000);

        Employee savedEmployee1 = employeeRepository.save(employee1);
        Employee savedEmployee2 = employeeRepository.save(employee2);
//        when then
        mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(pageSize)))
                .andExpect(jsonPath("$[0].id").value(savedEmployee1.getId()))
                .andExpect(jsonPath("$[1].id").value(savedEmployee2.getId()));
    }

    @Test
    void should_return_male_employees_when_get_employees_by_male_given_male() throws Exception {
//        given
        String gender = "male";
        String url = "/employees?gender=" + gender;
        Employee employee1 = new Employee("mht", 50, "male", 100000);
        Employee employee2 = new Employee("jack ma", 50, "female", 111111);

        Employee savedEmployee1 = employeeRepository.save(employee1);
        Employee savedEmployee2 = employeeRepository.save(employee2);
//        when then
        mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].gender").value(gender));
    }

    @Test
    void should_return_employee_when_get_employee_given_employee_id() throws Exception {
        //        given
        Employee employee = new Employee("mht", 50, "male", 100000);
        Employee savedEmployee = employeeRepository.save(employee);
        String url = "/employees/" + savedEmployee.getId();
//        when then
        mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(savedEmployee.getId()))
                .andExpect(jsonPath("$.name").value(savedEmployee.getName()))
                .andExpect(jsonPath("$.age").value(savedEmployee.getAge()))
                .andExpect(jsonPath("$.gender").value(savedEmployee.getGender()))
                .andExpect(jsonPath("$.salary").value(savedEmployee.getSalary()));
    }
}
