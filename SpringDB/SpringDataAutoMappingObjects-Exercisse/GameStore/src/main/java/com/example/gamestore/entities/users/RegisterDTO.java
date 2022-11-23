package com.example.gamestore.entities.users;


import com.example.gamestore.entities.exceptions.ValidationException;

public class RegisterDTO {

    private String fullName;

    private String email;

    private String password;

    private String confirmPassword;

    public RegisterDTO(String[] data) {

        this.email = data[1];
        this.password = data[2];
        this.confirmPassword = data[3];
        this.fullName = data[4];

        this.validateUser();
    }

    private void validateUser() {

        int indexOfAt = email.indexOf("@");
        int indexOfDot = email.indexOf(".");

        if (indexOfAt < 0 || indexOfDot < 0 || indexOfAt > indexOfDot) {
            throw new ValidationException("Email must contains . and @.");
        }

        if (!password.equals(confirmPassword)) {
            throw new ValidationException("Password and confirm password should be the same.");
        }

        if (fullName.length() < 3 && fullName.length() > 50) {
            throw new ValidationException("Full name should be more than 3 and less than 50 symbols.");
        }
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }
}
