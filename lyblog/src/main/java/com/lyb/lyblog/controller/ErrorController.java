package com.lyb.lyblog.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.lyb.lyblog.utils.Result;

@ControllerAdvice
public class ErrorController {

    // 处理所有未映射的路径
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseBody
    public ResponseEntity<Result> handleNoHandlerFoundException(NoHandlerFoundException exception) {
        Result result = Result.error().message("请求路径不存在").code(404);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
    }

    // 处理所有其他异常
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<Result> handleException(Exception exception) {
        Result result = Result.error().message("服务器内部错误").code(500);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
    }
}