package com.recom.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by rayd on 2017/3/28.
 */
@RestController
public class HelloController {
    @GetMapping(value = "/hello")
    public String hello() {
        return "hello";
}
}

