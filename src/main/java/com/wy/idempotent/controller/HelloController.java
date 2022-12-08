package com.wy.idempotent.controller;

import com.wy.idempotent.annotation.RepeatSubmit;
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

    @PostMapping("/hello")
    @RepeatSubmit(interval = 10000)
    public String hello() {
        return "hello";
    }

    @PostMapping("/hello2")
    public String hello2() {
        return "hello2";
    }
}
