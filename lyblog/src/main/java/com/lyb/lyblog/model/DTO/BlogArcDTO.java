package com.lyb.lyblog.model.DTO;

import com.lyb.lyblog.model.PO.Blogs;

import lombok.Data;

@Data
public class BlogArcDTO {
    Integer id;
    String day;
    String title;

    public BlogArcDTO(Blogs blogs) {
        this.id = blogs.getId();
        this.day = blogs.getCreateTime().getDayOfMonth() + "日";
        this.title = blogs.getTitle();
    }
}
