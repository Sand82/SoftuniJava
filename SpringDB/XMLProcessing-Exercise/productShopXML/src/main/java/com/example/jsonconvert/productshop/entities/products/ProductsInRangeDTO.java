package com.example.jsonconvert.productshop.entities.products;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductsInRangeDTO {

    @XmlElement(name = "product")
    List<ProductInRangeDTO> products;

    public ProductsInRangeDTO() {
    }

    public ProductsInRangeDTO(List<ProductInRangeDTO> products) {
        this.products = products;
    }

    public List<ProductInRangeDTO> getProducts() {
        return products;
    }


    public void setProducts(List<ProductInRangeDTO> products) {
        this.products = products;
    }
}
