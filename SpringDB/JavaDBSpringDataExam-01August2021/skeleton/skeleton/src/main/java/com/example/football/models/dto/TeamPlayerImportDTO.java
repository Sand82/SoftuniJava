package com.example.football.models.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement( name = "team")
@XmlAccessorType(XmlAccessType.FIELD)
public class TeamPlayerImportDTO {

    private String name;

    public TeamPlayerImportDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
