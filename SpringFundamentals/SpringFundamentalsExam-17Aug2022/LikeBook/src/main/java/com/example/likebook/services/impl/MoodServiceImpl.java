package com.example.likebook.services.impl;

import com.example.likebook.models.entities.Mood;
import com.example.likebook.models.entities.enums.MoodEnum;
import com.example.likebook.repositories.MoodRepository;
import com.example.likebook.services.MoodService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class MoodServiceImpl implements MoodService {

    private final MoodRepository moodRepository;

    public MoodServiceImpl(MoodRepository moodRepository) {
        this.moodRepository = moodRepository;
    }

    @Override
    public void initializeMoods() {

        if (moodRepository.count() == 0) {

            List<Mood> moods = Arrays.stream(MoodEnum.values()).map(m -> {
                        Mood mood = new Mood();
                        mood.setName(m);
                        return mood;
                    })
                    .toList();

            moodRepository.saveAll(moods);
        }
    }
}
