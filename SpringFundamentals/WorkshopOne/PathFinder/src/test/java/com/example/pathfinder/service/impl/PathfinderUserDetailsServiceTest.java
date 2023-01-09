package com.example.pathfinder.service.impl;

import com.example.pathfinder.model.entities.Role;
import com.example.pathfinder.model.entities.User;
import com.example.pathfinder.model.entities.enums.RoleEnums;
import com.example.pathfinder.repository.UserRepository;
import com.example.pathfinder.service.impl.impl.PathfinderUserDetailsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@ExtendWith(MockitoExtension.class)
public class PathfinderUserDetailsServiceTest {


    private User testUser;
    private PathfinderUserDetailsService serviceToTest;
    private Role adminRole;
    private Role userRole;
    @Mock
    private UserRepository mockUserRepository;

    @BeforeEach
    void init(){
        serviceToTest = new PathfinderUserDetailsService(mockUserRepository);

        adminRole = new Role();
        adminRole.setRole(RoleEnums.ADMIN);

        userRole = new Role();
        userRole.setRole(RoleEnums.USER);

        testUser = new User();
        testUser.setUsername("Sand").setEmail("sand@abv.bg").setRoles(List.of(adminRole, userRole)).setPassword("111111");
    }

    @Test
    void testUserNotFound() {
        Assertions.assertThrows(UsernameNotFoundException.class,() -> serviceToTest.loadUserByUsername("invalid_user_email@not_exist.com"));
    }

    @Test
    void userFound(){
        Mockito.when(mockUserRepository.findByEmail(testUser.getEmail())).thenReturn(Optional.of(testUser));

       var actual = serviceToTest.loadUserByUsername(testUser.getEmail());

       Assertions.assertEquals(actual.getUsername(), "sand@abv.bg");

       String actualRoles = actual.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(", "));

       String expectedRoles = "ROLE_ADMIN, ROLE_USER";

       Assertions.assertEquals(actualRoles, expectedRoles);
    }
}
