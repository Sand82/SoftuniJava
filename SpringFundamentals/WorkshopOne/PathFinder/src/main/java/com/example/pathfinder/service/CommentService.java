package com.example.pathfinder.service;

import com.example.pathfinder.model.view.CommentsViewModel;

import java.util.List;

public interface CommentService {
    List<CommentsViewModel> getComments(Long routeId);
}
