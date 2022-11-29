package com.example.football.models.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement( name = "town")
@XmlAccessorType(XmlAccessType.FIELD)
public class TownPlayerImportDTO {

    private String Name;

    public TownPlayerImportDTO() {
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
