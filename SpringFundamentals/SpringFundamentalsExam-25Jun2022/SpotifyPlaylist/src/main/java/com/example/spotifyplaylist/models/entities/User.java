package com.example.spotifyplaylist.models.entities;

import jakarta.persistence.*;
import jdk.jfr.Enabled;

import java.util.List;

@Entity
@Table(name = "users")
public class User extends BaseEntity{

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @ManyToMany
    private List<Song> playList;

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public List<Song> getPlayList() {
        return playList;
    }

    public User setPlayList(List<Song> playList) {
        this.playList = playList;
        return this;
    }
}
