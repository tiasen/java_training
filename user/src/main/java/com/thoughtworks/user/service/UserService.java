package com.thoughtworks.user.service;

import com.thoughtworks.user.model.User;
import com.thoughtworks.user.model.PageResult;

import java.util.Date;

public interface UserService {
    PageResult<User> findAll(int page, int size);
    User findById(Long id);
    long deleteById(Long id);
    User updateUser(User user);
    User addUser(User user);
    PageResult<User> findAllByName(String name, int page, int size);
    PageResult<User> findAllByAge(int age, int page, int size);
    PageResult<User> findAllByCreateBetween(Date startDate, Date endDate, int page, int size);
}
