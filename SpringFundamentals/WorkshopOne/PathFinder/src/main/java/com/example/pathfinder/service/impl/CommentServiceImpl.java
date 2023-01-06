package com.example.pathfinder.service.impl;

import com.example.pathfinder.model.entities.Route;
import com.example.pathfinder.model.entities.Comment;
import com.example.pathfinder.model.view.CommentsViewModel;
import com.example.pathfinder.repository.CommentRepository;
import com.example.pathfinder.repository.RouteRepository;
import com.example.pathfinder.service.CommentService;
import com.example.pathfinder.service.exceptions.ObjectNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    private RouteRepository routeRepository;
    private ModelMapper mapper;

    public CommentServiceImpl(CommentRepository commentRepository, RouteRepository routeRepository, ModelMapper mapper) {

        this.commentRepository = commentRepository;
        this.routeRepository = routeRepository;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public List<CommentsViewModel> getComments(Long routeId) {

        Optional<Route> route = routeRepository.findById(routeId);

        if (route.isEmpty()) {

            throw new ObjectNotFoundException("Route with route id " + routeId + " was not found!");
        }

//        List<Comment> comments = commentRepository.findByRouteId(routeId);
//
//        List<CommentsViewModel> models = comments.stream().map(c -> mapper.map(c, CommentsViewModel.class)).toList();

        return route.get().getComments().stream().map(this::mapAsComment).toList();
    }

    private CommentsViewModel mapAsComment(Comment comment) {

        CommentsViewModel model = new CommentsViewModel();

        model.setCommendId(comment.getId()).setCanApprove(true).setCanDelete(true).setCreated(comment.getCreated()).setMassage(comment.getTextContent()).setUser(comment.getAuthor().getFullName());

        return model;
    }
}
