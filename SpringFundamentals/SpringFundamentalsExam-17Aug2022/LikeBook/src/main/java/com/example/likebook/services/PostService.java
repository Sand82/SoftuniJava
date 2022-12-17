package com.example.likebook.services;

import com.example.likebook.models.views.MyPostViewModel;

import java.util.List;

public interface PostService {

    List<MyPostViewModel> getMyPosts(Long id);

    void deletePost(Long id);
}
