package softuni.exam.instagraphlite.models.dtos;

import org.hibernate.validator.constraints.URL;
import softuni.exam.instagraphlite.util.ValidatePicturePath;

import javax.persistence.Column;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class UsersImportDTO {

    private static ValidatePicturePath validatePicturePath;
    private String username;
    @Size(min = 2)
    private String password;
    @Size(min = 4)
    private String profilePicture;

    public UsersImportDTO() {

        validator();
    }

    public List<String> validator() {

        List<String> errors = new ArrayList<>();

        if ( username == null || username.trim().isEmpty() || username.length() < 2 || username.length() > 18) {

            errors.add("Username should be more than 2 and less than 18 symbols");
        }

        if (password == null || password.trim().isEmpty() || password.length() < 2) {

            errors.add("Password should be more than 2 symbols");
        }

       errors.addAll(validatePicturePath.validatePath( profilePicture));

        return errors;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }
}
