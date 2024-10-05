package com.lyb.lyblog.controller.client;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/content")
public class ContentController {
    @GetMapping("/tags")
    public String getTags(@RequestParam(required = false) String param) {
        return new String("hello world".getBytes());
    }

}
