package com.lyb.lyblog.model.DTO;

import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class BlogUptDTO {
    String title;
    String content;
    MultipartFile cover;
    Integer letters;
    Integer readTime;
    Boolean isReview;
    Boolean isPublish;
    Boolean isCommend;
    Boolean isTop;
    OffsetDateTime updateTime;
    List<Integer> tags;
    Integer categoryId;
}
