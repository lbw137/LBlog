package com.lyb.lyblog.model.DTO;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

import java.time.OffsetDateTime;
import java.util.List;

@Data
public class BlogPubDTO {
    String title;
    String content;
    MultipartFile cover;
    Integer letters;
    Integer readTime;
    Boolean isReview;
    Boolean isPublish;
    Boolean isCommend;
    Boolean isTop;
    OffsetDateTime createTime;
    OffsetDateTime publishTime;
    OffsetDateTime updateTime;
    List<Integer> tags;
    Integer categoryId;

}
