package com.wy.idempotent.controller;

import com.wy.idempotent.annotation.AutoIdempotent;
import com.wy.idempotent.token.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * todo desc
 *
 * @author 王渔
 * @date 2022/12/8 15:03
 */
@RestController
public class HelloController {
    @Autowired
    TokenService tokenService;

    @GetMapping("/gettoken")
    public String getToken() {
        return tokenService.createToken();
    }

    @PostMapping("/hello")
    @AutoIdempotent
    public String hello() {
        return "hello";
    }

    @PostMapping("/hello2")
    public String hello2() {
        return "hello2";
    }
}
