package com.example.coffeeshop.web;

import com.example.coffeeshop.models.bindings.OrderAddBindingModel;
import com.example.coffeeshop.services.OrderService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/add")
    public String add() {

        return "order-add";
    }

    @PostMapping("/add")
    public String confirmAdd(@Valid OrderAddBindingModel orderAddBindingModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasFieldErrors()) {

            redirectAttributes
                    .addFlashAttribute("orderAddBindingModel", orderAddBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.orderAddBindingModel", bindingResult);

            return "redirect:add";
        }

        orderService.addOrder(orderAddBindingModel);

        return "redirect:/";
    }

    @GetMapping("/ready/{id}")
    public String ready(@PathVariable Long id) {

        orderService.readyOrder(id);

        return "redirect:/";
    }

    @ModelAttribute
    public OrderAddBindingModel orderAddBindingModel() {

        return new OrderAddBindingModel();
    }
}
