package com.lyb.lyblog.model.VO;

import java.util.ArrayList;
import java.util.List;

import com.lyb.lyblog.model.DTO.BlogArcDTO;
import lombok.Data;

@Data
public class BlogArcVO {
    String date;
    List<BlogArcDTO> blogs;

    public BlogArcVO() {
        this.blogs = new ArrayList<>();
    }
}
