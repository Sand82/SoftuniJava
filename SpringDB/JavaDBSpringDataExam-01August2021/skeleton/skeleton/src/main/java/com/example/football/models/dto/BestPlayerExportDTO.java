package com.example.football.models.dto;

import javax.persistence.Column;
import javax.persistence.Entity;


public class BestPlayerExportDTO {

    @Column(name = "first-name")
    private String firstName;

    @Column(name = "last-name")
    private String lastName;

    private String position;

    private String teamName;

    private String stadiumName;

    public BestPlayerExportDTO() {
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getStadiumName() {
        return stadiumName;
    }

    public void setStadiumName(String stadiumName) {
        this.stadiumName = stadiumName;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        sb.append(String.format("Player - %s %s", firstName, lastName));
        sb.append(System.lineSeparator());
        sb.append(String.format("   Position - %s", position));
        sb.append(System.lineSeparator());
        sb.append(String.format("   Team - %s",teamName));
        sb.append(System.lineSeparator());
        sb.append(String.format("   Stadium - %s", stadiumName));
        sb.append(System.lineSeparator());

        return sb.toString();
    }
}
