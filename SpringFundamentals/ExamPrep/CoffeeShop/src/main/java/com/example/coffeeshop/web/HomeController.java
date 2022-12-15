package com.example.coffeeshop.web;

import com.example.coffeeshop.models.views.OrderViewModel;
import com.example.coffeeshop.models.views.UserOrdersViewModel;
import com.example.coffeeshop.repositories.OrderRepository;
import com.example.coffeeshop.security.CurrentUser;
import com.example.coffeeshop.services.OrderService;
import com.example.coffeeshop.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private CurrentUser currentUser;
    private OrderService orderService;
    private UserService userService;

    public HomeController(CurrentUser currentUser, OrderService orderService, UserService userService ) {
        this.currentUser = currentUser;
        this.orderService = orderService;
        this.userService = userService;
    }

    @GetMapping("/")
    public String index(Model model) {

        if (currentUser.getId() == null) {

            return "index";
        }

        List<OrderViewModel> orders = orderService.getAllOrders();

        int totalTime = getOrdersTime(orders);

        List<UserOrdersViewModel> users = orderService.getAllEmployees();

        model.addAttribute("orders", orders);
        model.addAttribute("users", users);
        model.addAttribute("totalTime", totalTime);

        return "home";
    }

    private int getOrdersTime(List<OrderViewModel> orders) {

        int result = 0;

        for (OrderViewModel order : orders) {

            int value =  order.getCategory().getNeededTime();

            result += value;
        }

        return result;
    }
}
