package com.example.coffeeshop.models.views;

public class UserOrdersViewModel {

    private  String username;

    private int countOfOrders;

    public UserOrdersViewModel() {
    }

    public String getUsername() {
        return username;
    }

    public UserOrdersViewModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public int getCountOfOrders() {
        return countOfOrders;
    }

    public UserOrdersViewModel setCountOfOrders(int countOfOrders) {
        this.countOfOrders = countOfOrders;
        return this;
    }

}
