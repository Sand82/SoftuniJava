package com.example.pathfinder.web.controllesrs;

import com.example.pathfinder.model.view.CommentsViewModel;
import com.example.pathfinder.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
public class CommentRestController {
    private CommentService commentService;
    public CommentRestController(CommentService commentService) {
        this.commentService = commentService;
    }
    @GetMapping("/api/{id}/comments")
    public ResponseEntity<List<CommentsViewModel>> getComments(
            @PathVariable Long id,
            Principal principal
    ) {
        System.out.println(id);
        List<CommentsViewModel> comments = commentService.getComments(id);

        return ResponseEntity.ok(comments);
    }
}
