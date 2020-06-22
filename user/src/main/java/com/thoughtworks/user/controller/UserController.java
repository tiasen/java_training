package com.thoughtworks.user.controller;

import com.thoughtworks.user.exception.UserInvalidException;
import com.thoughtworks.user.model.User;
import com.thoughtworks.user.model.PageResult;
import com.thoughtworks.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{page}/{size}")
    public PageResult<User> getAllUser(@PathVariable int page, @PathVariable int size) {
        return userService.findAll(page, size);
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.findById(id);
    }

    @PostMapping
    public User addUser(@RequestBody User User) {
        return userService.addUser(User);
    }

    @PutMapping
    public User updateUser(@RequestBody User User) {
        return userService.updateUser(User);
    }

    @DeleteMapping("/{id}")
    public long deleteUser(@PathVariable Long id) {
        return userService.deleteById(id);
    }

    @GetMapping("/{name}/{page}/{size}")
    public PageResult<User> getUsersByName(@PathVariable String name, @PathVariable int page, @PathVariable int size) {
      return userService.findAllByName(name, page, size);
    }

    @GetMapping("/{startDateStr}/{endDateStr}/{page}/{size}")
    public PageResult<User> getUserBetweenDate(@PathVariable String startDateStr, @PathVariable String endDateStr, @PathVariable int page, @PathVariable int size) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = null;
        Date endDate = null;

        try {
            startDate = sdf.parse(startDateStr);
            endDate = sdf.parse(endDateStr);
        } catch (ParseException e) {
            log.error("date format failed: {}", e);
            throw new UserInvalidException("日期格式无效");
        }
        return userService.findAllByCreateBetween(startDate, endDate, page, size);
    }
}
