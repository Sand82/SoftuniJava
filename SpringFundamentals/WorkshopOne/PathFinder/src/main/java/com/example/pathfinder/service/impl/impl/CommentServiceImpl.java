package com.example.pathfinder.service.impl.impl;

import com.example.pathfinder.model.entities.Route;
import com.example.pathfinder.model.entities.Comment;
import com.example.pathfinder.model.entities.User;
import com.example.pathfinder.model.services.CommentServiceModel;
import com.example.pathfinder.model.view.CommentsViewModel;
import com.example.pathfinder.repository.CommentRepository;
import com.example.pathfinder.repository.RouteRepository;
import com.example.pathfinder.repository.UserRepository;
import com.example.pathfinder.service.impl.CommentService;
import com.example.pathfinder.service.impl.exceptions.ObjectNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    private RouteRepository routeRepository;
    private UserRepository userRepository;
    private ModelMapper mapper;

    public CommentServiceImpl(CommentRepository commentRepository, RouteRepository routeRepository, UserRepository userRepository, ModelMapper mapper) {

        this.commentRepository = commentRepository;
        this.routeRepository = routeRepository;
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public List<CommentsViewModel> getComments(Long routeId) {

        Optional<Route> route = routeRepository.findById(routeId);

        if (route.isEmpty()) {

            throw new ObjectNotFoundException("Route with route id " + routeId + " was not found!");
        }

        return route.get().getComments().stream().map(this::mapAsComment).toList();
    }

    @Override
    public CommentsViewModel createComment(CommentServiceModel commentServiceModel) {

        var route = routeRepository.findById((commentServiceModel.getRouteId()))
                .orElseThrow(() -> new ObjectNotFoundException("Route with id " + commentServiceModel.getRouteId() +" not found."));

        User author = userRepository.findByEmail(commentServiceModel.getCreator())
                .orElseThrow(() -> new ObjectNotFoundException("User with email " + commentServiceModel.getCreator() +" not found."));

        Comment newComment = new Comment();
        newComment.setApprove(false);
        newComment.setTextContent(commentServiceModel.getMessage());
        newComment.setCreated(LocalDate.now());
        newComment.setRoute(route);
        newComment.setAuthor(author);

        Comment saveComment = commentRepository.save(newComment);

        return mapAsComment(saveComment);
    }

    private CommentsViewModel mapAsComment(Comment comment) {

        CommentsViewModel model = new CommentsViewModel();

        model.setCommendId(comment.getId()).setCanApprove(true).setCanDelete(true).setCreated(comment.getCreated()).setMassage(comment.getTextContent()).setUser(comment.getAuthor().getFullName());

        return model;
    }
}
