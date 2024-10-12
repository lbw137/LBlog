package com.lyb.lyblog.model.VO;

import com.lyb.lyblog.model.PO.Blogs;
import com.lyb.lyblog.model.PO.Categories;
import com.lyb.lyblog.model.PO.Tags;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class BlogDetailVO {
    Integer id;
    String title;
    String content;
    Integer views;
    Integer letters;
    Integer readTime;
    Boolean isReview;
    Boolean isCommend;
    Boolean isTop;
    LocalDateTime createTime;
    LocalDateTime publishTime;
    LocalDateTime updateTime;
    Categories category;
    List<Tags> tags;

    public BlogDetailVO(Blogs blogs) {
        this.id = blogs.getId();
        this.title = blogs.getTitle();
        this.content = blogs.getContent();
        this.views = blogs.getViews();
        this.letters = blogs.getLetters();
        this.readTime = blogs.getReadTime();
        this.isReview = blogs.getIsReview();
        this.isCommend = blogs.getIsCommend();
        this.isTop = blogs.getIsTop();
        this.createTime = blogs.getCreateTime();
        this.publishTime = blogs.getPublishTime();
        this.updateTime = blogs.getUpdateTime();
    }
}
