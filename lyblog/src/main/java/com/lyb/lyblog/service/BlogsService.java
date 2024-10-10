package com.lyb.lyblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyb.lyblog.mapper.BlogsMapper;
import com.lyb.lyblog.model.PO.BlogTag;
import com.lyb.lyblog.model.PO.Blogs;
import com.lyb.lyblog.model.VO.BlogListVO;

import xin.altitude.cms.common.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BlogsService extends ServiceImpl<BlogsMapper, Blogs> {
    @Autowired
    private CategoriesService categoriesService;
    @Autowired
    private TagsService tagsService;
    @Autowired
    private BlogTagService blogTagService;

    public List<BlogListVO> getBlogHome() {
        List<Blogs> blogs = this.list();
        List<BlogListVO> bloglist = EntityUtils.toList(blogs, BlogListVO::new);
        for (Integer i = 0; i < bloglist.size(); i++) {
            bloglist.get(i).setCategory(categoriesService.getCategoryById(blogs.get(i).getCategoryId()));
            bloglist.get(i).setTags(tagsService.getTagsByBlogId(blogs.get(i).getId()));
        }
        return bloglist;
    }

    public List<BlogListVO> getBlogsByTagId(Integer id) {
        List<BlogTag> BlogTags = blogTagService.getBlogsByTagId(id);
        // 提取所有文章ID
        Set<Integer> BlogIds = BlogTags.stream()
                .map(BlogTag::getBlogId)
                .collect(Collectors.toSet());
        List<Blogs> blogs = !BlogIds.isEmpty() ? this.listByIds(BlogIds) : new ArrayList<>();
        List<BlogListVO> bloglist = EntityUtils.toList(blogs, BlogListVO::new);
        for (Integer i = 0; i < bloglist.size(); i++) {
            bloglist.get(i).setCategory(categoriesService.getCategoryById(blogs.get(i).getCategoryId()));
            bloglist.get(i).setTags(tagsService.getTagsByBlogId(blogs.get(i).getId()));
        }
        return bloglist;
    }

    public List<BlogListVO> getBlogsByCatId(Integer id) {
        List<BlogListVO> bloglist = this.getBlogHome();
        // 筛选出文章ID在集合中的文章
        return bloglist.stream()
                .filter(item -> item.getCategory().getId() == id)
                .collect(Collectors.toList());
    }
}
