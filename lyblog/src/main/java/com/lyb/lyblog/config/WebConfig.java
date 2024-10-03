package com.lyb.lyblog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.lyb.lyblog.interceptor.JWTInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JWTInterceptor())
                .addPathPatterns("/user/**", "/category/**", "/tag/**") // 匹配以这些路径开头的请求
                .excludePathPatterns("/user/login", "/user/refreshToken"); // 排除特定的路径
    }
}