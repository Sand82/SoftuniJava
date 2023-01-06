package com.example.pathfinder.model.view;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class CommentsViewModel {
    private long commendId;
    private String massage;
    private String user;
    private LocalDate created;
    private boolean canApprove;
    private boolean canDelete;

    public CommentsViewModel() {
    }

    public long getCommendId() {
        return commendId;
    }

    public CommentsViewModel setCommendId(long commendId) {
        this.commendId = commendId;
        return this;
    }

    public String getMassage() {
        return massage;
    }

    public CommentsViewModel setMassage(String massage) {
        this.massage = massage;
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
