package com.example.likebook.services.impl;

import com.example.likebook.models.bindings.MoodAddBindingModel;
import com.example.likebook.models.entities.Mood;
import com.example.likebook.models.entities.Post;
import com.example.likebook.models.entities.User;
import com.example.likebook.models.entities.enums.MoodEnum;
import com.example.likebook.repositories.MoodRepository;
import com.example.likebook.repositories.PostRepository;
import com.example.likebook.services.PostService;
import com.example.likebook.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final ModelMapper mapper;
    private final UserService userService;
    private final MoodRepository moodRepository;

    public PostServiceImpl(PostRepository postRepository, ModelMapper mapper, UserService userService,
                           MoodRepository moodRepository) {
        this.postRepository = postRepository;
        this.mapper = mapper;
        this.userService = userService;
        this.moodRepository = moodRepository;
    }
}
