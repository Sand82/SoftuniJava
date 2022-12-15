package com.example.likebook.init;

import com.example.likebook.services.MoodService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInit implements CommandLineRunner {

    private final MoodService moodService;

    public DatabaseInit(MoodService moodService) {
        this.moodService = moodService;
    }

    @Override
    public void run(String... args) throws Exception {
        moodService.initializeMoods();
    }
}
