package com.example.jsonconvert.productshop.entities.category;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

import static javax.xml.bind.annotation.XmlAccessType.*;

@XmlRootElement(name = "categories")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoryImportDTO {

    @XmlElement(name = "category")
    private List<CategoryNameDto> categories;

    public CategoryImportDTO(){

    }

    public List<CategoryNameDto> getCategories() {
        return categories;
    }
}
