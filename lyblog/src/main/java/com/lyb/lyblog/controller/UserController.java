package com.lyb.lyblog.controller;

import org.springframework.web.bind.annotation.RestController;

import com.lyb.lyblog.model.DTO.UserLoginDTO;
import com.lyb.lyblog.model.PO.User;
import com.lyb.lyblog.service.UserService;
import com.lyb.lyblog.utils.JWTUtils;
import com.lyb.lyblog.utils.Result;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController()
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    // RESTful API 接口
    @GetMapping("/{id}")
    public Result getUserById(@PathVariable int id) {
        return Result.ok().data("user", userService.getById(id));
    }

    @PostMapping("/login")
    public Result Login(@RequestBody UserLoginDTO userDto, HttpServletResponse response) {
        String username = userDto.getUsername();
        String password = userDto.getPassword();
        // 验证用户信息
        User user = userService.checkUser(username, password);
        if (user != null) {
            // 生成token
            String token = JWTUtils.getToken(user.getId(), username);
            response.setHeader("Authorization", token);
            return Result.ok().message("登录成功");
        } else {
            return Result.error().message("用户名或密码错误");
        }
    }

    @PutMapping("")
    public String update(User user) {
        return "更新用户";
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable int id) {
        return "根据ID删除用户";
    }
}
