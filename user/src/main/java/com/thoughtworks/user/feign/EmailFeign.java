package com.thoughtworks.user.feign;

import com.thoughtworks.user.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "email", configuration = FeignConfig.class, fallback = EmailFeignFallback.class)
public interface EmailFeign {

    @GetMapping("/email/{id}")
    String getEmail(@PathVariable long id);
}
