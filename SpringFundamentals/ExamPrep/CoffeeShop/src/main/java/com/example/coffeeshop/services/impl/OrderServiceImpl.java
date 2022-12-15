package com.example.coffeeshop.services.impl;

import com.example.coffeeshop.models.bindings.OrderAddBindingModel;
import com.example.coffeeshop.models.entities.Category;
import com.example.coffeeshop.models.entities.Order;
import com.example.coffeeshop.models.entities.User;
import com.example.coffeeshop.models.views.OrderViewModel;
import com.example.coffeeshop.models.views.UserOrdersViewModel;
import com.example.coffeeshop.repositories.OrderRepository;
import com.example.coffeeshop.repositories.UserRepository;
import com.example.coffeeshop.security.CurrentUser;
import com.example.coffeeshop.services.CategoryService;
import com.example.coffeeshop.services.OrderService;
import com.example.coffeeshop.services.UserService;
import org.hibernate.mapping.Collection;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;
    private CategoryService categoryService;
    private UserService userService;
    private CurrentUser currentUser;
    private ModelMapper mapper;
    private final UserRepository userRepository;

    public OrderServiceImpl(OrderRepository orderRepository, CategoryService categoryService, UserService userService, CurrentUser currentUser, ModelMapper mapper,
                            UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.categoryService = categoryService;
        this.userService = userService;
        this.currentUser = currentUser;
        this.mapper = mapper;
        this.userRepository = userRepository;
    }

    @Override
    public void addOrder(OrderAddBindingModel orderAddBindingModel) {

        Order order = mapper.map(orderAddBindingModel, Order.class);

        Category category = categoryService.getByCategoryNameEnum(orderAddBindingModel.getCategory());

        System.out.println(currentUser.getId());

        User user = userService.getById(currentUser.getId());

        order.setCategory(category);

        order.setEmployee(user);

        saveOrder(order);
    }

    @Override
    public List<OrderViewModel> getAllOrders() {

        List<OrderViewModel> orders = orderRepository.findAllByOrderByPriceDesc().stream().map(o -> mapper.map(o, OrderViewModel.class)).toList();

        return orders;
    }

    @Override
    public List<UserOrdersViewModel> getAllEmployees() {

        List<UserOrdersViewModel> models = userService.getAllModels();

        return models;
    }

    @Override
    public void readyOrder(Long id) {
        orderRepository.deleteById(id);
    }

    private void saveOrder(Order order) {

        orderRepository.save(order);
    }
}
