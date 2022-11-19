package com.example.spring_dat_lab;

import com.example.spring_dat_lab.models.User;
import com.example.spring_dat_lab.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ConsoleRunner implements CommandLineRunner {

    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) throws Exception {

        User firstUser = new User("Mish", 7);
        userService.registerUser(firstUser);

        User secondUser = new User("Mish", 7);
        userService.registerUser(firstUser);
    }
}
