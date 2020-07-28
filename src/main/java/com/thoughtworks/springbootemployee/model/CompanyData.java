package com.thoughtworks.springbootemployee.model;

import java.util.ArrayList;
import java.util.List;

public class CompanyData {
    private List<Company> companies=new ArrayList<>();

    public CompanyData() {
        this.companies.add(new Company(1,"ali",1));
        this.companies.add(new Company(2,"tx",2));
        this.companies.add(new Company(3,"hw",3));
        this.companies.add(new Company(4,"zj",3));
        this.companies.add(new Company(5,"oocl",3));
        this.companies.add(new Company(6,"yy",3));
        this.companies.add(new Company(7,"meituan",3));
    }

    public List<Company> getCompanies() {
        return companies;
    }

}
