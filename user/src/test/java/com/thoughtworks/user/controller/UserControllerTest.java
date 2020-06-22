package com.thoughtworks.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.user.dto.PageMeta;
import com.thoughtworks.user.model.PageResult;
import com.thoughtworks.user.model.User;
import com.thoughtworks.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static com.thoughtworks.user.fixture.UserTestFixture.user;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith({SpringExtension.class})
class UserControllerTest {

    @TestConfiguration
    public static class Config {
        @MockBean
        UserService userService;
    }

    private MockMvc mvc;

    @Autowired
    UserService userService;

    @Autowired
    WebApplicationContext webApplicationContext;

    @BeforeEach
    void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void getAllUser() throws Exception {
        PageResult<User> pageResult = PageResult.<User>builder().meta(PageMeta.builder().page(0)
                .size(2)
                .total(10)
                .build()).data(List.of(user(), user())).build();
        when(userService.findAll(1, 2)).thenReturn(pageResult);
        mvc.perform(get("/user/{page}/{size}", 1, 2))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.length()").value(2));
//                .andExpect(jsonPath("$.mate.size", is(2)))
//                .andExpect(jsonPath("$.mate.total", is(10)))
//                .andExpect(jsonPath("$.mate.page", is(0)));

    }

    @Test
    void getUser() throws Exception {
        when(userService.findById(1L)).thenReturn(user());


        mvc.perform(get("/user/{id}", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id", is(1)))
                .andExpect(jsonPath("name", is("test")))
                .andExpect(jsonPath("age", is(18)));
    }

    @Test
    void addUser() throws Exception {
        when(userService.addUser(user())).thenReturn(user());

        ObjectMapper objectMapper = new ObjectMapper();
        String userJson = objectMapper.writeValueAsString(user());

        mvc.perform(post("/user").contentType(MediaType.APPLICATION_JSON).content(userJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id", is(1)))
                .andExpect(jsonPath("name", is("test")))
                .andExpect(jsonPath("age", is(18)));
    }

    @Test
    void updateUser() throws Exception {

        when(userService.updateUser(user())).thenReturn(user());
        ObjectMapper objectMapper = new ObjectMapper();
        String userJson = objectMapper.writeValueAsString(user());

        mvc.perform(put("/user").contentType(MediaType.APPLICATION_JSON).content(userJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id", is(1)))
                .andExpect(jsonPath("name", is("test")))
                .andExpect(jsonPath("age", is(18)));
    }

    @Test
    void deleteUser() throws Exception {
        when(userService.deleteById(1L)).thenReturn(1L);

        mvc.perform(delete("/user/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(1)));
    }
}