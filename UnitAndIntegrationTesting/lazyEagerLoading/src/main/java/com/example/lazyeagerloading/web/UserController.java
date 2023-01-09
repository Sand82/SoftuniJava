package com.example.lazyeagerloading.web;

import com.example.lazyeagerloading.models.entities.UserEntity;
import com.example.lazyeagerloading.repositories.RoleRepository;
import com.example.lazyeagerloading.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class UserController {

    private UserRepository userRepository;

    private RoleRepository roleRepository;


    public UserController(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @GetMapping("/users")
    @Transactional
    public String getUsers(){
        System.out.println("--------------------------");
        System.out.println("Get all users");
        List<UserEntity> users = userRepository.findAll();
        System.out.println("Get all users: [" + users.size() + "]");

        for (UserEntity user : users) {
            System.out.println(user.getName());
            System.out.println(user.getRoles().stream().map(r -> r.getName()).collect(Collectors.joining(", ")));
        }

        return "hello";
    }
}
