package com.example.gamestore.entities.users;

public class LoginDTO {

    //TODO class validation
    private String email;

    private String password;

    public LoginDTO(String [] data) {

        this.email = data[1];
        this.password = data[2];
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
