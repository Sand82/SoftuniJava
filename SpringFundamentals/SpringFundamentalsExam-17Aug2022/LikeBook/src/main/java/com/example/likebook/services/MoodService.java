package com.example.likebook.services;

import com.example.likebook.models.bindings.MoodAddBindingModel;
import com.example.likebook.models.entities.enums.MoodEnum;

public interface MoodService {
    void initializeMoods();
    void createMood(MoodAddBindingModel moodAddBindingModel);
}
