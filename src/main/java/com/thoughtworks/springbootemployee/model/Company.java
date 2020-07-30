package com.thoughtworks.springbootemployee.model;

import javax.persistence.*;
import java.util.List;
@Entity
@Table(name = "company")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "company_name")
    private String companyName;

    private Integer employeesNumber;
    @OneToMany(targetEntity = Employee.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "companyId")
    private List<Employee> employees;

    public Company() {
    }

    public Company(int id, String companyName, Integer employeesNumber) {
        this.id = id;
        this.companyName = companyName;
        this.employeesNumber = employeesNumber;
    }

    public Company(int id, List<Employee> employees) {
        this.id = id;
        this.employees = employees;
        this.employeesNumber += employees.size();
    }

    public void addEmployee(Employee employee) {
        this.employees.add(employee);
        this.employeesNumber += 1;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> Employees) {
        this.employees = Employees;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getEmployeesNumber() {
        return employeesNumber;
    }

    public void setEmployeesNumber(int employeesNumber) {
        this.employeesNumber = employeesNumber;
    }


}
