package com.example.likebook.models.bindings;

import com.example.likebook.models.entities.enums.MoodEnum;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class MoodAddBindingModel {

    @NotNull
    private MoodEnum mood;

    @NotNull
    @Size(min = 2, max = 150)
    private String content;

    public MoodAddBindingModel() {
    }

    public MoodEnum getMood() {
        return mood;
    }

    public MoodAddBindingModel setMood(MoodEnum mood) {
        this.mood = mood;
        return this;
    }

    public String getContent() {
        return content;
    }

    public MoodAddBindingModel setContent(String content) {
        this.content = content;
        return this;
    }
}
