package com.example.pathfinder.web;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.pathfinder.model.bindings.NewCommentBindingModel;
import com.example.pathfinder.model.entities.Comment;
import com.example.pathfinder.model.entities.Route;
import com.example.pathfinder.model.entities.User;
import com.example.pathfinder.model.entities.enums.LevelEnum;
import com.example.pathfinder.repository.CommentRepository;
import com.example.pathfinder.repository.RouteRepository;
import com.example.pathfinder.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.bytebuddy.matcher.ElementMatchers;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.RouteMatcher;

import java.time.LocalDate;
import java.util.List;

@WithMockUser("Sand")
@SpringBootTest
@AutoConfigureMockMvc
public class CommentRestControllerTest {

    private static String COMMENT_1 = "Good route for walk";
    private static String COMMENT_2 = "Grate route for walk";
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private RouteRepository routeRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private User testUser;

    @BeforeEach
    void SetUp() {
        testUser = new User();
        testUser.setUsername("Sand")
                .setPassword("111111")
                .setEmail("sand@abv.bg")
                .setFullName("Sand Stef");

        testUser = userRepository.save(testUser);
    }

    @AfterEach
    void tearDown() {
        routeRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    void testGetComments() throws Exception {

        long routeId = initComment().getId();

        mockMvc.perform(get("/api/" + routeId + "/comments"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$.[1].massage", Matchers.is(COMMENT_2)))
                .andExpect(jsonPath("$.[0].massage", Matchers.is(COMMENT_1)));
    }

    @Test
    void testCreateComments() throws Exception {
        NewCommentBindingModel model = new NewCommentBindingModel()
                .setMassage(COMMENT_1);

        var emptyRoute = initRoute();

        mockMvc.perform(post("/api/" + emptyRoute.getId() + "/comments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(model))
                .accept(MediaType.APPLICATION_JSON)
                .with(csrf())

        )
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

    }

    private Route initRoute() {
        Route testRoute = new Route();
        testRoute.setName("Test");
        testRoute.setGpxCoordinates("1111");

        testRoute.setLevel(LevelEnum.BEGINNER);
        testRoute.setVideoUrl("asdsada");

        routeRepository.save(testRoute);

        return testRoute;
    }

    private Route initComment() {

        Route testRoute = initRoute();

        Comment commentOne = new Comment();
        commentOne.setTextContent(COMMENT_1);
        commentOne.setAuthor(testUser);
        commentOne.setCreated(LocalDate.now());
        commentOne.setApprove(true);
        commentOne.setRoute(testRoute);

        Comment commentTwo = new Comment();
        commentTwo.setTextContent(COMMENT_2);
        commentTwo.setAuthor(testUser);
        commentTwo.setCreated(LocalDate.now());
        commentTwo.setApprove(true);
        commentTwo.setRoute(testRoute);

        testRoute.setComments(List.of(commentOne, commentTwo));

        return routeRepository.save(testRoute);
    }
}
