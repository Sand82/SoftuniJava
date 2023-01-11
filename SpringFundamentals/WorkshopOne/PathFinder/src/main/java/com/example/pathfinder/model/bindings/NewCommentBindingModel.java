package com.example.pathfinder.model.bindings;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class NewCommentBindingModel {

    @NotBlank
    @Size(min=10)
    private String message;

    public NewCommentBindingModel() {
    }

    public String getMessage() {
        return message;
    }

    public NewCommentBindingModel setMessage(String message) {
        this.message = message;
        return this;
    }
}
