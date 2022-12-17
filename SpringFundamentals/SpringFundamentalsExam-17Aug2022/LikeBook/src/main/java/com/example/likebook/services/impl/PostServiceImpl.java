package com.example.likebook.services.impl;

import com.example.likebook.models.entities.Post;
import com.example.likebook.models.entities.User;
import com.example.likebook.models.views.MyPostViewModel;
import com.example.likebook.models.views.OtherUserViewModel;
import com.example.likebook.repositories.MoodRepository;
import com.example.likebook.repositories.PostRepository;
import com.example.likebook.security.CurrentUser;
import com.example.likebook.services.PostService;
import com.example.likebook.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final ModelMapper mapper;
    private final UserService userService;
    private final MoodRepository moodRepository;
    private final CurrentUser currentUser;

    public PostServiceImpl(PostRepository postRepository,
                           ModelMapper mapper,
                           UserService userService,
                           MoodRepository moodRepository, CurrentUser currentUser) {
        this.postRepository = postRepository;
        this.mapper = mapper;
        this.userService = userService;
        this.moodRepository = moodRepository;
        this.currentUser = currentUser;
    }

    @Override
    public List<MyPostViewModel> getMyPosts(Long id) {

        List<Post> posts = postRepository.findByUserId(id);

        List<MyPostViewModel> models = new ArrayList<>();

        for (Post post : posts) {

            MyPostViewModel model = new MyPostViewModel();

            model.setId(post.getId())
                .setContent(post.getContent())
                .setMoodName(post.getMood().getName())
                .setUserLikes(post.getUserLikes().size());

            models.add(model);
        }

        return models;
    }

    @Override
    public void deletePost(Long id) {

        postRepository.deleteById(id);
    }

    @Override
    public List<OtherUserViewModel> getOtherPosts() {

       List<Post> posts =  postRepository
               .findAll()
               .stream()
               .filter(p -> p.getUser().getId() != currentUser.getId())
               .toList();

       List<OtherUserViewModel> models = new ArrayList<>();

        for (Post post : posts) {

            OtherUserViewModel model = new OtherUserViewModel();

            User user = userService.getUserBuId(post.getUser().getId());

            model.setId(post.getId())
                    .setContent(post.getContent())
                    .setMood(post.getMood().getName().name())
                    .setLikes(post.getUserLikes().size())
                    .setUsername(user.getUsername());

            models.add(model);
        }

        return models;
    }

    @Override
    public void setLike(Long id) {

       Post post = postRepository.findById(id).orElse(null);

       User user = userService.getUserBuId(currentUser.getId());

       User userLike = post.getUserLikes().stream().filter(p -> p.getId() == user.getId()).findFirst().orElse(null);

        if (userLike == null) {
            post.addLike(user);

            postRepository.save(post);
        }
    }
}
