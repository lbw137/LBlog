package com.lyb.lyblog.model.VO;

import com.lyb.lyblog.model.PO.Blogs;
import com.lyb.lyblog.model.PO.Categories;
import com.lyb.lyblog.model.PO.Tags;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class BlogListVO {
    Integer id;
    String title;
    String cover;
    Integer views;
    Integer letters;
    Integer readTime;
    boolean isTop;
    LocalDateTime createTime;
    Categories category;
    List<Tags> tags;

    public BlogListVO(Blogs blogs) {
        this.id = blogs.getId();
        this.title = blogs.getTitle();
        this.cover = blogs.getCover();
        this.views = blogs.getViews();
        this.letters = blogs.getLetters();
        this.readTime = blogs.getReadTime();
        this.isTop = blogs.isTop();
        this.createTime = blogs.getCreateTime();
    }
}
