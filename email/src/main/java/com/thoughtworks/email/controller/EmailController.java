package com.thoughtworks.email.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
public class EmailController {

    @GetMapping("/{id}")
    public String getEmail(@PathVariable long id) {
        return id + "@rest.local";
    }
}
