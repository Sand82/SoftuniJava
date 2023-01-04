package com.example.errors.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ObjectNotFoundExceptions.class)
    public ModelAndView handleDbException(ObjectNotFoundExceptions e) {

        ModelAndView modelAndView = new ModelAndView("object-not-found");
        modelAndView.addObject("message", e.getMessage());
        modelAndView.setStatus(HttpStatus.NOT_FOUND);

        return modelAndView;
    }
}
