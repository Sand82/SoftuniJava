package com.example.football.models.dto;

import com.example.football.models.entity.Town;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.*;
import java.time.LocalDate;

@XmlRootElement( name = "player")
@XmlAccessorType(XmlAccessType.FIELD)
public class PlayerImportDTO {

    @XmlElement(name = "first-name")
    @Size(min = 2)
    private String firstName;

    @XmlElement(name = "last-name")
    @Size(min = 2)
    private String lastName;

    @XmlElement
    @Email
    private String email;

    @XmlElement(name = "birth-date")
    private String birthDate;

    @XmlElement
    private String position;

    @XmlElement( name = "town")
    private TownPlayerImportDTO town;

    @XmlElement( name = "team")
    private TeamPlayerImportDTO team;

    @XmlElement(name = "stat")
    private StatPlayerImportDTO stat;


    public PlayerImportDTO() {
    }

    public StatPlayerImportDTO getStat() {
        return stat;
    }

    public void setStat(StatPlayerImportDTO stat) {
        this.stat = stat;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public TownPlayerImportDTO getTown() {
        return town;
    }

    public void setTown(TownPlayerImportDTO town) {
        this.town = town;
    }

    public TeamPlayerImportDTO getTeam() {
        return team;
    }

    public void setTeam(TeamPlayerImportDTO team) {
        this.team = team;
    }
}
