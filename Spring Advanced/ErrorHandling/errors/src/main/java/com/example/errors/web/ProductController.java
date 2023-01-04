package com.example.errors.web;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProductController {

    @GetMapping("/products/{id}/details")
    public String showproductDetails(@PathVariable String id) {

        throw new ProductNotFoundException("Product whit id " + id + " was not found!");
    }

    @GetMapping("/products/{id}/error")
    public String boom(@PathVariable String id) {

        throw new NullPointerException();
    }

    @ExceptionHandler({ProductNotFoundException.class})
    public ModelAndView handleDbException(ProductNotFoundException e) {

        ModelAndView modelAndView = new ModelAndView("product-not-found");
        modelAndView.addObject("message", e.getMessage());
        modelAndView.setStatus(HttpStatus.NOT_FOUND);

        return modelAndView;
    }
}
