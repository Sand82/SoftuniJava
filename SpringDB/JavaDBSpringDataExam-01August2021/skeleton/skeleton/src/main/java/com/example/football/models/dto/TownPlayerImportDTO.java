package com.example.football.models.dto;

import javax.persistence.Column;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement( name = "town")
@XmlAccessorType(XmlAccessType.FIELD)
public class TownPlayerImportDTO {

    @Column(name = "name")
    private String name;

    public TownPlayerImportDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        name = name;
    }
}
