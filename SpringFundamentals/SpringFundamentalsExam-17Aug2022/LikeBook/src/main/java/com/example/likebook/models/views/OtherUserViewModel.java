package com.example.likebook.models.views;

public class OtherUserViewModel {

    private Long id;

    private String username;

    private String content;

    private String mood;

    private Integer likes;

    public OtherUserViewModel() {
    }

    public Long getId() {
        return id;
    }

    public OtherUserViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public OtherUserViewModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getContent() {
        return content;
    }

    public OtherUserViewModel setContent(String content) {
        this.content = content;
        return this;
    }

    public String getMood() {
        return mood;
    }

    public OtherUserViewModel setMood(String mood) {
        this.mood = mood;
        return this;
    }

    public Integer getLikes() {
        return likes;
    }

    public OtherUserViewModel setLikes(Integer likes) {
        this.likes = likes;
        return this;
    }
}
