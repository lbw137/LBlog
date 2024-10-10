package com.lyb.lyblog.model.VO;

import java.util.List;

import com.lyb.lyblog.model.PO.Blogs;
import com.lyb.lyblog.model.PO.Categories;
import com.lyb.lyblog.model.PO.Tags;

import lombok.Data;

@Data
public class SiteInfoVO {
    List<Blogs> blogs;
    List<Tags> tags;
    List<Categories> categories;
}
