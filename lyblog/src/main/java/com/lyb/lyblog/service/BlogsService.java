package com.lyb.lyblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyb.lyblog.mapper.BlogsMapper;
import com.lyb.lyblog.model.DTO.BlogArcDTO;
import com.lyb.lyblog.model.PO.BlogTag;
import com.lyb.lyblog.model.PO.Blogs;
import com.lyb.lyblog.model.VO.BlogArcVO;
import com.lyb.lyblog.model.VO.BlogDetailVO;
import com.lyb.lyblog.model.VO.BlogEditVO;
import com.lyb.lyblog.model.VO.BlogListAdVO;
import com.lyb.lyblog.model.VO.BlogListVO;

import xin.altitude.cms.common.util.EntityUtils;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
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

    public List<BlogListVO> getBlogsHome() {
        LambdaQueryWrapper<Blogs> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Blogs::getIsPublish, true);
        List<Blogs> blogs = this.list(queryWrapper);
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
        LambdaQueryWrapper<Blogs> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Blogs::getIsPublish, true);
        if (!BlogIds.isEmpty())
            queryWrapper.in(Blogs::getId, BlogIds);
        else
            return new ArrayList<>();
        List<Blogs> blogs = this.list(queryWrapper);
        List<BlogListVO> bloglist = EntityUtils.toList(blogs, BlogListVO::new);
        for (Integer i = 0; i < bloglist.size(); i++) {
            bloglist.get(i).setCategory(categoriesService.getCategoryById(blogs.get(i).getCategoryId()));
            bloglist.get(i).setTags(tagsService.getTagsByBlogId(blogs.get(i).getId()));
        }
        return bloglist;
    }

    public List<BlogListVO> getBlogsByCatId(Integer id) {
        LambdaQueryWrapper<Blogs> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Blogs::getIsPublish, true);
        queryWrapper.eq(Blogs::getCategoryId, id);
        List<Blogs> blogs = this.list(queryWrapper);
        List<BlogListVO> bloglist = EntityUtils.toList(blogs, BlogListVO::new);
        for (Integer i = 0; i < bloglist.size(); i++) {
            bloglist.get(i).setCategory(categoriesService.getCategoryById(blogs.get(i).getCategoryId()));
            bloglist.get(i).setTags(tagsService.getTagsByBlogId(blogs.get(i).getId()));
        }
        return bloglist;
    }

    public BlogDetailVO getBlogById(Integer id) {
        LambdaQueryWrapper<Blogs> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Blogs::getId, id);
        Blogs blog = this.getOne(queryWrapper);
        BlogDetailVO blogDetail = EntityUtils.toObj(blog, BlogDetailVO::new);
        blogDetail.setCategory(categoriesService.getCategoryById(blog.getCategoryId()));
        blogDetail.setTags(tagsService.getTagsByBlogId(blog.getId()));
        return blogDetail;
    }

    public List<BlogArcVO> getBlogArchivist() {
        List<Blogs> blogs = this.list();
        // 日期应该越早的排在前面
        blogs.sort((o1, o2) -> o2.getCreateTime().compareTo(o1.getCreateTime()));
        // 每一个年月是一组
        List<BlogArcVO> blogArchivist = new ArrayList<>();
        BlogArcDTO blogArcDTO;
        for (Blogs blog : blogs) {
            int cur = blogArchivist.size() - 1;
            blogArcDTO = EntityUtils.toObj(blog, BlogArcDTO::new);
            BlogArcVO blogArcVO = new BlogArcVO();
            blogArcVO.setDate(blog.getCreateTime().format(DateTimeFormatter.ofPattern("yyyy年MM月")));
            if (blogArchivist.isEmpty()) {
                blogArchivist.add(blogArcVO);
                cur = blogArchivist.size() - 1;
                blogArchivist.get(cur).getBlogs().add(blogArcDTO);
            } else {
                if (blogArchivist.get(cur).getDate().equals(blogArcVO.getDate())) {
                    blogArchivist.get(cur).getBlogs().add(blogArcDTO);
                } else {
                    blogArchivist.add(blogArcVO);
                    cur = blogArchivist.size() - 1;
                    blogArchivist.get(cur).getBlogs().add(blogArcDTO);
                }
            }
        }
        return blogArchivist;
    }

    public List<BlogListAdVO> getBlogListAd() {
        List<Blogs> blogs = this.list();
        List<BlogListAdVO> bloglist = EntityUtils.toList(blogs, BlogListAdVO::new);
        for (Integer i = 0; i < bloglist.size(); i++) {
            bloglist.get(i).setCategory(categoriesService.getCategoryById(blogs.get(i).getCategoryId()));
        }
        return bloglist;
    }

    public BlogEditVO getBlogByIdAd(Integer id) {
        LambdaQueryWrapper<Blogs> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Blogs::getId, id);
        Blogs blog = this.getOne(queryWrapper);
        BlogEditVO blogEditVO = EntityUtils.toObj(blog, BlogEditVO::new);
        blogEditVO.setTags(blogTagService.getTagIdsByBlogId(blog.getId()));
        return blogEditVO;
    }

    public Boolean updatePublish(Integer id, Boolean isPublish) {
        LambdaUpdateWrapper<Blogs> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(Blogs::getId, id);
        wrapper.set(Blogs::getIsPublish, isPublish);
        wrapper.set(Blogs::getPublishTime, OffsetDateTime.now());
        return this.update(wrapper);
    }
}
