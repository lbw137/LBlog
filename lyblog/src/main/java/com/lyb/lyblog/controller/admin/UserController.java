package com.lyb.lyblog.controller.admin;

import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.lyb.lyblog.model.DTO.UserLoginDTO;
import com.lyb.lyblog.model.PO.User;
import com.lyb.lyblog.service.UserService;
import com.lyb.lyblog.utils.JWTUtils;
import com.lyb.lyblog.utils.Result;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController()
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/test")
    public Result test() {
        return Result.ok().message("测试成功");
    }

    @PostMapping("/login")
    public Result Login(@RequestBody UserLoginDTO userDto, HttpServletResponse response) {
        String username = userDto.getUsername();
        String password = userDto.getPassword();
        // 验证用户信息
        User user = userService.checkUser(username, password);
        if (user != null) {
            // 生成token
            String access_token = JWTUtils.getAccessToken(user.getId(), username);
            String refresh_token = JWTUtils.getRefreshToken(user.getId(), username);
            return Result.ok().message("登录成功")
                    .data("access_token", access_token)
                    .data("refresh_token", refresh_token);
        } else {
            return Result.error().message("用户名或密码错误").code(403);
        }
    }

    @GetMapping("/refreshToken")
    public Result refreshToken(HttpServletRequest request) {
        Result result = new Result();
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            // 提取令牌
            String token = authHeader.substring("Bearer ".length()).trim();
            try {
                Map<String, Claim> payload = JWTUtils.getPayload(token);
                // 返回新token
                String accessToken = JWTUtils.getAccessToken(payload.get("id").asLong(),
                        payload.get("username").asString());
                String refreshToken = JWTUtils.getRefreshToken(payload.get("id").asLong(),
                        payload.get("username").asString());
                return Result.ok().message("token刷新成功")
                        .data("access_token", accessToken).data("refresh_token", refreshToken);
            } catch (SignatureVerificationException e) {
                e.printStackTrace();
                result.setMessage("无效签名");
            } catch (TokenExpiredException e) {
                e.printStackTrace();
                result.setMessage("登陆过期，请重新登录");
            } catch (AlgorithmMismatchException e) {
                e.printStackTrace();
                result.setMessage("token算法不一致");
            } catch (Exception e) {
                e.printStackTrace();
                result.setMessage("token失效");
            }
            result.setSuccess(false);
            return result.code(401);
        }
        return Result.error().message("用户尚未登陆");
    }
}
