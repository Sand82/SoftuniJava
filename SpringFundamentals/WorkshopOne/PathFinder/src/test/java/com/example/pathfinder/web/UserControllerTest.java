package com.example.pathfinder.web;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.pathfinder.model.entities.User;
import com.example.pathfinder.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    private static final String USERNAME = "Sand";
    private static final String FULLNAME = "SandStef";
    private static final String EMAIL = "Sand@abv.bg";
    private static final String AGE = "40";
    private static final String PASSWORD = "12345";
    private static final String CONFIRMPASSWORD = "12345";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Test
    void testOpenRegisterFrom() throws Exception {
        mockMvc.perform(get("/users/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("register"));
    }

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    void testRegisterUser() throws Exception {
        mockMvc.perform(post("/users/register")
                        .param("username", USERNAME)
                        .param("fullName", FULLNAME)
                        .param("email", EMAIL)
                        .param("age", AGE)
                        .param("password", PASSWORD)
                        .param("confirmPassword", CONFIRMPASSWORD)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                )
                .andExpect(status().is3xxRedirection());

        Assertions.assertEquals(1, userRepository.count());

        Optional<User> newlyCreatedUserOpt = userRepository.findByEmail(EMAIL);

        Assertions.assertTrue(newlyCreatedUserOpt.isPresent());

        User newlyCreatedUser = newlyCreatedUserOpt.get();

        Assertions.assertEquals(newlyCreatedUser.getFullName(), FULLNAME);
        Assertions.assertEquals(newlyCreatedUser.getUsername(), USERNAME);
        Assertions.assertEquals(newlyCreatedUser.getEmail(), EMAIL);
    }
}
