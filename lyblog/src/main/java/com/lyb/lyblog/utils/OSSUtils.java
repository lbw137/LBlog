package com.lyb.lyblog.utils;

import java.io.IOException;
import java.util.Properties;

import org.springframework.web.multipart.MultipartFile;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.UUID;

public class OSSUtils {
    private static final String endpoint = "oss-cn-guangzhou.aliyuncs.com";

    private static final String domain = "https://lyb1013-image.oss-cn-guangzhou.aliyuncs.com/";

    // 从环境变量获取 Access Key ID 和 Access Key Secret
    private static final String accessKeyId = System.getenv("ALIYUN_ACCESS_KEY_ID");

    private static final String accessKeySecret = System.getenv("ALIYUN_ACCESS_KEY_SECRET");

    private static final String bucketName = "lyb1013-image";

    public static String uploadFile(MultipartFile file) throws OSSException, ClientException, IOException {
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        // 获取文件名称
        String originalFilename = file.getOriginalFilename();
        // 获取文件后缀
        String ext = FileUtil.extName(originalFilename);
        String uuid = UUID.randomUUID().toString().replace("-", "");
        String fileName = uuid + "." + ext;
        // 上传oss文件
        ossClient.putObject(bucketName, fileName, file.getInputStream());
        ossClient.shutdown();
        return domain + fileName;
    }
}
