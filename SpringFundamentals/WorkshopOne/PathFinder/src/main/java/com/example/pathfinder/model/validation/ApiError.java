package com.example.pathfinder.model.validation;

import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

public class ApiError {

    private HttpStatus httpStatus;
    private List<String> fieldWithErrors = new ArrayList<>();

    public ApiError(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public void addErrors(String error) {

        fieldWithErrors.add(error);
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public ApiError setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
        return this;
    }

    public List<String> getFieldWithErrors() {
        return fieldWithErrors;
    }

    public ApiError setFieldWithErrors(List<String> fieldWithErrors) {
        this.fieldWithErrors = fieldWithErrors;
        return this;
    }
}
