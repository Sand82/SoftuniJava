package com.example.likebook.models.views;

import com.example.likebook.models.entities.User;
import com.example.likebook.models.entities.enums.MoodEnum;

import java.util.List;

public class MyPostViewModel {

    private Long id;

    private String content;

    private MoodEnum moodName;

    private Integer userLikes;

    public MyPostViewModel() {
    }

    public String getContent() {
        return content;
    }

    public MyPostViewModel setContent(String content) {
        this.content = content;
        return this;
    }

    public MoodEnum getMoodName() {
        return moodName;
    }

    public MyPostViewModel setMoodName(MoodEnum moodName) {
        this.moodName = moodName;
        return this;
    }

    public Integer getUserLikes() {
        return userLikes;
    }

    public MyPostViewModel setUserLikes(Integer userLikes) {
        this.userLikes = userLikes;
        return this;
    }

    public Long getId() {
        return id;
    }

    public MyPostViewModel setId(Long id) {
        this.id = id;
        return this;
    }
}
