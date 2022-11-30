package softuni.exam.models.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

public class AgentImportDTO {

    @Column(name = "first-name")
    @Size(min = 2)
    private String firstName;

    @Column(name = "last-name")
    @Size(min = 2)
    private String lastName;

    @Email
    private String email;

    private String town;

    public AgentImportDTO() {
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

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }
}
