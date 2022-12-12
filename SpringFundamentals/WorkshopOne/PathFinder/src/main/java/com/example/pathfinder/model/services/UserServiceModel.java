package com.example.pathfinder.model.services;

import com.example.pathfinder.model.entities.Role;
import com.example.pathfinder.model.entities.enums.LevelEnum;

import java.util.Set;

public class UserServiceModel {

    private Long id;
    private String username;

    private String fullName;

    private String email;

    private Integer age;

    private String password;

    private LevelEnum level;

    private Set<Role> role;

    public UserServiceModel() {
    }

    public String getUsername() {
        return username;
    }

    public UserServiceModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public UserServiceModel setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserServiceModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public UserServiceModel setAge(Integer age) {
        this.age = age;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserServiceModel setPassword(String password) {
        this.password = password;
        return this;
    }

    public Long getId() {
        return id;
    }

    public UserServiceModel setId(Long id) {
        this.id = id;
        return this;
    }

    public LevelEnum getLevel() {
        return level;
    }

    public UserServiceModel setLevel(LevelEnum level) {
        this.level = level;
        return this;
    }

    public Set<Role> getRole() {
        return role;
    }

    public UserServiceModel setRole(Set<Role> role) {
        this.role = role;
        return this;
    }
}
