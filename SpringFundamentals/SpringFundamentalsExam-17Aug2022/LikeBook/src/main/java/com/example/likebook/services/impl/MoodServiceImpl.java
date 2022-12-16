package com.example.likebook.services.impl;

import com.example.likebook.models.bindings.MoodAddBindingModel;
import com.example.likebook.models.entities.Mood;
import com.example.likebook.models.entities.Post;
import com.example.likebook.models.entities.User;
import com.example.likebook.models.entities.enums.MoodEnum;
import com.example.likebook.repositories.MoodRepository;
import com.example.likebook.repositories.PostRepository;
import com.example.likebook.repositories.UserRepository;
import com.example.likebook.security.CurrentUser;
import com.example.likebook.services.MoodService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class MoodServiceImpl implements MoodService {

    private final MoodRepository moodRepository;
    private final PostRepository postRepository;
    private UserRepository userRepository;
    private CurrentUser currentUser;

    public MoodServiceImpl(MoodRepository moodRepository,
                           PostRepository postRepository,
                           UserRepository userRepository,
                           CurrentUser currentUser) {
        this.moodRepository = moodRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.currentUser = currentUser;
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

    @Override
    public void createMood(MoodAddBindingModel moodAddBindingModel) {

        Post post = new Post();

        Mood mood = moodRepository.findByName(moodAddBindingModel.getMood());

        User user = userRepository.findById(currentUser.getId()).orElse(null);

        post.setMood(mood);
        post.setUser(user);
        post.setContent(moodAddBindingModel.getContent());

        postRepository.save(post);
    }
}
