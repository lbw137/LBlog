package com.lyb.lyblog.model.VO;

import java.time.OffsetDateTime;

import com.lyb.lyblog.model.PO.Blogs;
import com.lyb.lyblog.model.PO.Categories;

import lombok.Data;

@Data
public class BlogListAdVO {
    Integer id;
    String title;
    OffsetDateTime createTime;
    OffsetDateTime updateTime;
    Categories category;
    Boolean isPublish;

    public BlogListAdVO(Blogs blogs) {
        this.id = blogs.getId();
        this.title = blogs.getTitle();
        this.createTime = blogs.getCreateTime();
        this.updateTime = blogs.getUpdateTime();
        this.isPublish = blogs.getIsPublish();
    }
}
