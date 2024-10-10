package com.lyb.lyblog.model.PO;

import com.baomidou.mybatisplus.annotation.TableField;

import lombok.Data;

@Data
public class BlogTag {
    @TableField("blog_id")
    Integer blogId;
    @TableField("tag_id")
    Integer tagId;
}
