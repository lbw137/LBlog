package com.lyb.lyblog.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyb.lyblog.mapper.TagsMapper;
import com.lyb.lyblog.model.PO.BlogTag;
import com.lyb.lyblog.model.PO.Tags;

@Service
public class TagsService extends ServiceImpl<TagsMapper, Tags> {
    @Autowired
    private BlogTagService blogTagService;

    public List<Tags> getTagsByBlogId(Integer id) {
        List<BlogTag> BlogTags = blogTagService.getTagsByBlogId(id);
        // 提取所有标签ID
        Set<Integer> tagIds = BlogTags.stream()
                .map(BlogTag::getTagId)
                .collect(Collectors.toSet());
        // 使用提取的标签ID来查询标签列表
        LambdaQueryWrapper<Tags> wrapper = new LambdaQueryWrapper<>();
        wrapper.in(Tags::getId, tagIds);
        return this.list(wrapper);
    }
}
