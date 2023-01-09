package com.example.lazyeagerloading.Init;

import com.example.lazyeagerloading.models.entities.RoleEntity;
import com.example.lazyeagerloading.models.entities.UserEntity;
import com.example.lazyeagerloading.repositories.RoleRepository;
import com.example.lazyeagerloading.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DbInitialization implements CommandLineRunner {

    private UserRepository userRepository;
    private RoleRepository roleRepository;

    public DbInitialization(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }


    @Override
    public void run(String... args) throws Exception {

        if (userRepository.count() == 0) {

            RoleEntity adminRole = new RoleEntity().setName("ADMIN");
            RoleEntity userRole = new RoleEntity().setName("USER");

            roleRepository.saveAll(List.of(adminRole, userRole));

            UserEntity user1 = new UserEntity().setName("Sand").setRoles(List.of(adminRole, userRole));
            UserEntity user2 = new UserEntity().setName("Mim").setRoles(List.of(userRole));

            userRepository.saveAll(List.of(user1, user2));
        }
    }
}
