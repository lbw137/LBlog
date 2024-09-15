package com.lyb.lyblog.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletRequest;

import java.io.File;
import java.io.IOException;

import org.springframework.web.bind.annotation.PostMapping;

@RestController
public class FileUploadController {
    @PostMapping("/upload")
    public String up(String username, MultipartFile photo, HttpServletRequest request) throws IOException {
        System.out.println(username);
        // 获取图片的原始名称
        System.out.println(photo.getOriginalFilename());
        // 获取文件类型
        System.out.println(photo.getContentType());

        String path = request.getServletContext().getRealPath("/upload/");
        System.out.println(path);
        saveFile(photo, path);
        return "上传成功";
    }

    public void saveFile(MultipartFile photo, String path) throws IOException {
        // 判断存储的目录是否存在，如果不存在则创建
        File dir = new File(path);
        if (!dir.exists())
            dir.mkdirs();

        File file = new File(path + photo.getOriginalFilename());
        photo.transferTo(file);
    }
}
