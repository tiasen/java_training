package com.thoughtworks.user.service;

import com.thoughtworks.user.dao.UserDao;
import com.thoughtworks.user.entity.UserEntity;
import com.thoughtworks.user.model.PageResult;
import com.thoughtworks.user.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith({SpringExtension.class})
class UserServiceTest {

    @TestConfiguration
    public static class Config {
        @MockBean
        UserService userService;

        @MockBean
        UserDao userDao;
    }

    @Autowired
    UserDao userDao;

    @Autowired
    UserService userService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void findAll() {
        Page<UserEntity> userEntities =
                new PageImpl<UserEntity>(List.of(UserEntity.builder().build(), UserEntity.builder().build()), PageRequest.of(1, 20), 4L);

        when(userDao.findAll(PageRequest.of(1, 20))).thenReturn(userEntities);
        PageResult<User> userPageResult = userService.findAll(1, 20);
        assertThat(userPageResult.getData().size()).isEqualTo(2);
        assertThat(userPageResult.getMeta().getPage()).isEqualTo(1);
        assertThat(userPageResult.getMeta().getSize()).isEqualTo(20);
        assertThat(userPageResult.getMeta().getTotal()).isEqualTo(4);
    }

    @Test
    void findById() {
    }

    @Test
    void deleteById() {
    }

    @Test
    void updateUser() {
    }

    @Test
    void addUser() {
    }

    @Test
    void findAllByName() {
    }

    @Test
    void findAllByAge() {
    }

    @Test
    void findAllByCreateBetween() {
    }
}