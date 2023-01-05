package com.example.aop.SLA;

public class Driver {

  private String name;

  private String licenceCategory;

    public Driver() {
    }

    public String getName() {
        return name;
    }

    public Driver setName(String name) {
        this.name = name;
        return this;
    }

    public String getLicenceCategory() {
        return licenceCategory;
    }

    public Driver setLicenceCategory(String licenceCategory) {
        this.licenceCategory = licenceCategory;
        return this;
    }
}
