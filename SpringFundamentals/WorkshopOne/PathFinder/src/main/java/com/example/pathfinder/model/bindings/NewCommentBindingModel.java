package com.example.pathfinder.model.bindings;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class NewCommentBindingModel {

    @NotBlank
    @Size(min=10)
    private String massage;

    public NewCommentBindingModel() {
    }

    public String getMassage() {
        return massage;
    }

    public NewCommentBindingModel setMassage(String massage) {
        this.massage = massage;
        return this;
    }
}
