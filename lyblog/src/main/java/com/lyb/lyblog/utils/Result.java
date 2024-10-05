package com.lyb.lyblog.utils;

import java.util.HashMap;
import java.util.Map;

public class Result {
    private int code;
    private String message;
    private Map<String, Object> data = new HashMap<>();

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public static Result ok() {
        Result result = new Result();
        result.setCode(200);
        result.setMessage("成功");
        return result;
    }

    public static Result error() {
        Result result = new Result();
        result.setCode(401);
        result.setMessage("失败");
        return result;
    }

    public Result data(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

    public Result data(Map<String, Object> data) {
        this.setData(data);
        return this;
    }

    public Result message(String message) {
        this.message = message;
        return this;
    }

    public Result code(int code) {
        this.code = code;
        return this;
    }
}
