package com.lyb.lyblog.model.VO;

import java.util.List;
import com.lyb.lyblog.model.PO.Blogs;
import lombok.Data;

@Data
public class BlogEditVO {
    String title;
    String content;
    String coverUrl;
    Boolean isReview;
    Boolean isPublish;
    Boolean isCommend;
    Boolean isTop;
    List<Integer> tags;
    Integer categoryId;

    public BlogEditVO(Blogs blog) {
        this.title = blog.getTitle();
        this.content = blog.getContent();
        this.coverUrl = blog.getCover();
        this.isReview = blog.getIsReview();
        this.isPublish = blog.getIsPublish();
        this.isCommend = blog.getIsCommend();
        this.isTop = blog.getIsTop();
        this.categoryId = blog.getCategoryId();
    }
}
