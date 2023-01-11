package com.example.pathfinder.web;

import com.example.pathfinder.model.bindings.NewCommentBindingModel;
import com.example.pathfinder.model.services.CommentServiceModel;
import com.example.pathfinder.model.validation.ApiError;
import com.example.pathfinder.model.view.CommentsViewModel;
import com.example.pathfinder.service.impl.CommentService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.security.Principal;
import java.util.List;

@RestController
public class CommentRestController {
    private CommentService commentService;
    private ModelMapper mapper;
    public CommentRestController(CommentService commentService, ModelMapper mapper) {
        this.commentService = commentService;
        this.mapper = mapper;
    }
    @GetMapping("/api/{id}/comments")
    public ResponseEntity<List<CommentsViewModel>> getComments(
            @PathVariable Long id,
            Principal principal
    ) {

        List<CommentsViewModel> comments = commentService.getComments(id);

        return ResponseEntity.ok(comments);
    }

    @PostMapping("/api/{id}/comments")
    public ResponseEntity<CommentsViewModel> newComment(
            @AuthenticationPrincipal UserDetails principal,
            @PathVariable Long id,
            @RequestBody @Valid NewCommentBindingModel newCommentBindingModel){

        CommentServiceModel bindingModel = new CommentServiceModel();
        bindingModel.setRouteId(id).setMessage(newCommentBindingModel.getMessage()).setCreator(principal.getUsername());

        CommentsViewModel newComment = commentService.createComment(bindingModel);

        URI locationOfNewComment =
                URI.create(String.format("/api/%s/comments/%s", id, newComment.getCommentId()));

        return ResponseEntity.created(locationOfNewComment).body(newComment);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> onValidationFailure(MethodArgumentNotValidException exc) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
        exc.getFieldErrors().forEach(f -> apiError.addErrors(f.getField()));

        return ResponseEntity.badRequest().body(apiError);
    }
}
