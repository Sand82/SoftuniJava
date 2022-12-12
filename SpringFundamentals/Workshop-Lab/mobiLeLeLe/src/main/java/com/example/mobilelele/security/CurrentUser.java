package com.example.mobilelele.security;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class CurrentUser {

    private String name = "Anonymous";
    private boolean isAnonymous;

    private boolean setLoggedin;

    private String firstName;

    private String lastName;

    private String userName;

    public CurrentUser() {
    }

    public String getName() {
        return name;
    }

    public CurrentUser setName(String name) {
        this.name = name;
        return this;
    }

    public boolean isAnonymous() {
        return isAnonymous;
    }

    public CurrentUser setAnonymous(boolean anonymous) {

        if (anonymous) {

            this.name = name;
        }
        isAnonymous = anonymous;
        return this;
    }

    public boolean isSetLoggedin() {
        return setLoggedin;
    }

    public CurrentUser setSetLoggedin(boolean setLoggedin) {
        this.setLoggedin = setLoggedin;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public CurrentUser setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public CurrentUser setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public CurrentUser setUserName(String userName) {
        this.userName = userName;
        return this;
    }
}
