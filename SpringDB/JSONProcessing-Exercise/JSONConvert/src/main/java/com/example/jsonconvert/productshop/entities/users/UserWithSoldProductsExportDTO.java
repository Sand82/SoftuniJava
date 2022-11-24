package com.example.jsonconvert.productshop.entities.users;

import com.example.jsonconvert.productshop.entities.products.SoldProductsDTO;

import java.util.List;

public class UserWithSoldProductsExportDTO {

    private String firstName;

    private String lastName;

    private List<SoldProductsDTO> boughtItems;


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<SoldProductsDTO> getBoughtItems() {
        return boughtItems;
    }

    public void setBoughtItems(List<SoldProductsDTO> boughtItems) {
        this.boughtItems = boughtItems;
    }
}
