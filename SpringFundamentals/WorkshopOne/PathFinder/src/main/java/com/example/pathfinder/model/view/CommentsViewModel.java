package com.example.pathfinder.model.view;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class CommentsViewModel {
    private long commentId;
    private String message;
    private String user;
    private LocalDate created;
    private boolean canApprove;
    private boolean canDelete;

    public CommentsViewModel() {
    }


    public long getCommentId() {
        return commentId;
    }

    public CommentsViewModel setCommentId(long commentId) {
        this.commentId = commentId;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public CommentsViewModel setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getUser() {
        return user;
    }

    public CommentsViewModel setUser(String user) {
        this.user = user;
        return this;
    }

    public LocalDate getCreated() {
        return created;
    }

    public CommentsViewModel setCreated(LocalDate created) {
        this.created = created;
        return this;
    }

    public boolean isCanApprove() {
        return canApprove;
    }

    public CommentsViewModel setCanApprove(boolean canApprove) {
        this.canApprove = canApprove;
        return this;
    }

    public boolean isCanDelete() {
        return canDelete;
    }

    public CommentsViewModel setCanDelete(boolean canDelete) {
        this.canDelete = canDelete;
        return this;
    }
}
