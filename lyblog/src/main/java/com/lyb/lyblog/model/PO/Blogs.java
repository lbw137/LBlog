package com.lyb.lyblog.model.PO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
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
    LocalDateTime createTime;
    @TableField("publish_time")
    LocalDateTime publishTime;
    @TableField("update_time")
    LocalDateTime updateTime;
    @TableField("category_id")
    Integer categoryId;
}
