package com.example.jsonconvert.productshop.entities.category;

import javax.persistence.AccessType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.lang.reflect.Field;

import static javax.xml.bind.annotation.XmlAccessType.FIELD;

@XmlRootElement(name = "category")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoryNameDto {

    @XmlElement
    private String name;

    public CategoryNameDto() {

    }

    public String getName() {
        return name;
    }
}
