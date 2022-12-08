package com.example.mobilelele.security;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class CurrentUser {

    private String name = "Anonymous";
    private boolean isAnonymous;

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
}
