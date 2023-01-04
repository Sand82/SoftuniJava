package com.example.errors.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class OrdersController {

    @GetMapping("/orders/{id}/details")
    public String showProductDetails(@PathVariable String id) {

        throw new ObjectNotFoundExceptions("Object whit id " + id + " was not found!");
    }
}
