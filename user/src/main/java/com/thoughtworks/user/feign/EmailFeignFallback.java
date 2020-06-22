package com.thoughtworks.user.feign;

import org.springframework.stereotype.Component;

@Component
public class EmailFeignFallback implements EmailFeign {
    @Override
    public String getEmail(long id) {
        return "fallback";
    }
}
