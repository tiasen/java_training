package com.thoughtworks.user.fixture;


import com.thoughtworks.user.model.User;

import java.text.ParseException;

public class UserTestFixture {
    public static User user() throws ParseException {
        return User.builder().id(1L).age(18).name("test").build();
    }
}
