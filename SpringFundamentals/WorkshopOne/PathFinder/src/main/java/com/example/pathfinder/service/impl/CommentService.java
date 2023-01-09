package com.example.pathfinder.service.impl;

import com.example.pathfinder.model.services.CommentServiceModel;
import com.example.pathfinder.model.view.CommentsViewModel;

import java.util.List;

public interface CommentService {
    List<CommentsViewModel> getComments(Long routeId);

    CommentsViewModel createComment(CommentServiceModel commentServiceModel);
}
