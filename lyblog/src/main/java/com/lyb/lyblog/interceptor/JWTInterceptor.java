package com.lyb.lyblog.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lyb.lyblog.utils.JWTUtils;
import com.lyb.lyblog.utils.Result;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JWTInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        Result result = new Result();
        String Authorization = request.getHeader("Authorization");
        if (Authorization == null || Authorization.split(" ").length == 1) {
            result.setMessage("未登录");
            result.setCode(401);
            String json = new ObjectMapper().writeValueAsString(result);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().print(json);
            return false;
        }
        String token = Authorization.split(" ")[1];
        try {
            JWTUtils.verifyToken(token);
            return true;// 放行请求
        } catch (SignatureVerificationException e) {
            e.printStackTrace();
            result.setMessage("无效签名");
        } catch (TokenExpiredException e) {
            e.printStackTrace();
            result.setMessage("accessToken过期");
        } catch (AlgorithmMismatchException e) {
            e.printStackTrace();
            result.setMessage("token算法不一致");
        } catch (Exception e) {
            e.printStackTrace();
            result.setMessage("token失效");
        }
        result.setCode(401);
        String json = new ObjectMapper().writeValueAsString(result);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print(json);
        return false;
    }
}
