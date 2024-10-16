package com.lyb.lyblog.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyb.lyblog.mapper.BlogTagMapper;
import com.lyb.lyblog.model.PO.BlogTag;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class BlogTagService extends ServiceImpl<BlogTagMapper, BlogTag> {
    public List<BlogTag> getTagsByBlogId(Integer id) {
        LambdaQueryWrapper<BlogTag> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(BlogTag::getBlogId, id);
        return this.list(wrapper);
    }

    public List<Integer> getTagIdsByBlogId(Integer id) {
        LambdaQueryWrapper<BlogTag> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(BlogTag::getBlogId, id);
        wrapper.select(BlogTag::getTagId);
        return this.listObjs(wrapper);
    }

    public List<BlogTag> getBlogsByTagId(Integer id) {
        LambdaQueryWrapper<BlogTag> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(BlogTag::getTagId, id);
        return this.list(wrapper);
    }

    public void removeTags(Integer blogId, List<Integer> tags) {
        LambdaQueryWrapper<BlogTag> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(BlogTag::getBlogId, blogId).in(BlogTag::getTagId, tags);
        this.remove(wrapper);
    }

    public void saveTags(Integer blogId, List<Integer> tags) {
        // 收集所有 BlogTag 对象
        List<BlogTag> blogTags = tags.stream()
                .map(tagId -> {
                    BlogTag blogTag = new BlogTag();
                    blogTag.setBlogId(blogId);
                    blogTag.setTagId(tagId);
                    return blogTag;
                })
                .collect(Collectors.toList());
        // 批量保存 BlogTag 对象
        if (!blogTags.isEmpty()) {
            this.saveBatch(blogTags); // 使用 MyBatis-Plus 的 saveBatch 方法
        }
    }
}
