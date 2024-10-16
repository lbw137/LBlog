package com.lyb.lyblog.model.PO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.lyb.lyblog.model.DTO.BlogPubDTO;
import com.lyb.lyblog.model.DTO.BlogUptDTO;

import java.time.OffsetDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Blogs {
    @TableId(type = IdType.AUTO)
    Integer id;
    String title;
    String content;
    String cover;
    Integer views;
    Integer letters;
    @TableField("read_time")
    Integer readTime;
    @TableField("is_review")
    Boolean isReview;
    @TableField("is_publish")
    Boolean isPublish;
    @TableField("is_commend")
    Boolean isCommend;
    @TableField("is_top")
    Boolean isTop;
    @TableField("create_time")
    OffsetDateTime createTime;
    @TableField("publish_time")
    OffsetDateTime publishTime;
    @TableField("update_time")
    OffsetDateTime updateTime;
    @TableField("category_id")
    Integer categoryId;

    public Blogs(BlogPubDTO blogPubDTO) {
        this.title = blogPubDTO.getTitle();
        this.content = blogPubDTO.getContent();
        this.letters = blogPubDTO.getLetters();
        this.readTime = blogPubDTO.getReadTime();
        this.isReview = blogPubDTO.getIsReview();
        this.isPublish = blogPubDTO.getIsPublish();
        this.isCommend = blogPubDTO.getIsCommend();
        this.isTop = blogPubDTO.getIsTop();
        this.categoryId = blogPubDTO.getCategoryId();
        this.createTime = blogPubDTO.getCreateTime();
        this.publishTime = blogPubDTO.getPublishTime();
        this.updateTime = blogPubDTO.getUpdateTime();
    }

    public Blogs(BlogUptDTO blogUptDTO) {
        this.title = blogUptDTO.getTitle();
        this.content = blogUptDTO.getContent();
        this.letters = blogUptDTO.getLetters();
        this.readTime = blogUptDTO.getReadTime();
        this.isReview = blogUptDTO.getIsReview();
        this.isPublish = blogUptDTO.getIsPublish();
        this.isCommend = blogUptDTO.getIsCommend();
        this.isTop = blogUptDTO.getIsTop();
        this.categoryId = blogUptDTO.getCategoryId();
        this.updateTime = blogUptDTO.getUpdateTime();
    }
}
