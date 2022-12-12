package com.example.pathfinder.model.view;

import com.example.pathfinder.model.entities.enums.LevelEnum;

public class UserViewModel {

    private long id;

    private String username;

    private int age;

    private String fullName;

    private LevelEnum level;

    public UserViewModel() {
    }

    public long getId() {
        return id;
    }

    public UserViewModel setId(long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserViewModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public int getAge() {
        return age;
    }

    public UserViewModel setAge(int age) {
        this.age = age;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public UserViewModel setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public LevelEnum getLevel() {
        return level;
    }

    public UserViewModel setLevel(LevelEnum level) {
        this.level = level;
        return this;
    }
}
