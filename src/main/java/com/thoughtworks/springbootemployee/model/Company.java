package com.thoughtworks.springbootemployee.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class Company {
    private int id;
    private List<Employee> employees;
    private String companyName;
    private int employeesNumber;

    public Company() {
    }

    public Company(int id, String companyName, int employeesNumber) {
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

    public int getId() {
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
