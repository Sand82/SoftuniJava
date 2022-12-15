package com.example.coffeeshop.services;

import com.example.coffeeshop.models.bindings.OrderAddBindingModel;
import com.example.coffeeshop.models.views.OrderViewModel;
import com.example.coffeeshop.models.views.UserOrdersViewModel;

import java.util.List;

public interface OrderService {
    void addOrder(OrderAddBindingModel orderAddBindingModel);

    List<OrderViewModel> getAllOrders();

    List<UserOrdersViewModel> getAllEmployees();

    void readyOrder(Long id);
}
