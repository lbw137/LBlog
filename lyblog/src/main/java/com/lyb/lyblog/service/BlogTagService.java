package com.lyb.lyblog.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyb.lyblog.mapper.BlogTagMapper;
import com.lyb.lyblog.model.PO.BlogTag;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class BlogTagService extends ServiceImpl<BlogTagMapper, BlogTag> {
    public List<BlogTag> getTagsByBlogId(Integer id) {
        LambdaQueryWrapper<BlogTag> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(BlogTag::getBlogId, id);
        return this.list(wrapper);
    }

    public List<BlogTag> getBlogsByTagId(Integer id) {
        LambdaQueryWrapper<BlogTag> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(BlogTag::getTagId, id);
        return this.list(wrapper);
    }
}
