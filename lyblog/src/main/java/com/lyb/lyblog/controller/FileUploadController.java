package com.lyb.lyblog.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.lyb.lyblog.utils.OSSUtils;
import com.lyb.lyblog.utils.Result;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import java.io.IOException;

import org.springframework.web.bind.annotation.PostMapping;

@RestController
public class FileUploadController {
    @PostMapping("/admin/upload")
    public Result upload(@RequestBody MultipartFile file) throws IOException {
        return Result.ok().data("fileUrl", OSSUtils.uploadFile(file));
    }
}
