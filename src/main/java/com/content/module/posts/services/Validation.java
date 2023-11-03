package com.content.module.posts.services;

import com.content.module.posts.dtos.TokenValidation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "validation", url = "http://localhost:8082/auth")
public interface Validation {

    @RequestMapping(method = RequestMethod.POST, value = "/validation", produces = "application/json")
    TokenValidation validateToken(@RequestParam("tokenKey") String tokenKey);
}
