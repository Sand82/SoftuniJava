package com.example.jsonconvert.productshop.entities.users;

import javax.persistence.Column;

public class UserImportDTO {

    private String firstName;
    private String lastName;
    private Integer age;

    public UserImportDTO(String firstName, String lastName, Integer age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getAge() {
        return age;
    }
}
