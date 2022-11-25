package com.example.jsonconvert.productshop.entities.products;

import com.example.jsonconvert.productshop.entities.User;
import org.springframework.beans.factory.annotation.Value;

import java.math.BigDecimal;

public class ProductInRenegeExportDTO {

    private String name;

    private BigDecimal price;

    private String seller;

    public ProductInRenegeExportDTO(String name, BigDecimal price, String firstName, String lastName) {
        this.name = name;
        this.price = price;

        if (firstName == null) {
            this.seller = lastName;
        } else {
            this.seller = firstName +' '+ lastName;
        }
    }
    //    @Value("#{target.seller.firstName + ' ' + target.seller.lastName}")
//    String getSeller(); // on interface class


    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getSeller() {
        return seller;
    }
}
