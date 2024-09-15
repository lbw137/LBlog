package com.lyb.lyblog.controller;

import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public String getHello(@RequestParam(required = false) String name) {
        return "Hello " + name;
    }

}
