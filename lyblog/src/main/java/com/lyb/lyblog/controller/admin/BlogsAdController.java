package com.lyb.lyblog.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSException;
import com.lyb.lyblog.model.DTO.BlogPubDTO;
import com.lyb.lyblog.model.DTO.BlogUptDTO;
import com.lyb.lyblog.model.PO.Blogs;
import com.lyb.lyblog.model.VO.BlogEditVO;
import com.lyb.lyblog.model.VO.BlogListAdVO;
import com.lyb.lyblog.service.BlogTagService;
import com.lyb.lyblog.service.BlogsService;
import com.lyb.lyblog.utils.OSSUtils;
import com.lyb.lyblog.utils.Result;

import xin.altitude.cms.common.util.EntityUtils;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/admin/blogs")
@Transactional
public class BlogsAdController {
    @Autowired
    private BlogsService blogsService;
    @Autowired
    private BlogTagService blogTagService;

    @GetMapping("")
    public Result getBlogByIdAd(@RequestParam Integer id) {
        BlogEditVO blog = blogsService.getBlogByIdAd(id);
        return Result.ok().data("blog", blog);
    }

    @GetMapping("/list")
    public Result getBlogListAd() {
        List<BlogListAdVO> blogs = blogsService.getBlogListAd();
        return Result.ok().data("blogs", blogs);
    }

    @PutMapping("/update/{id}")
    public Result putMethodName(@PathVariable Integer id, @ModelAttribute BlogUptDTO blogUptDTO)
            throws OSSException, ClientException, IOException {
        Blogs blog = EntityUtils.toObj(blogUptDTO, Blogs::new);
        blog.setId(id);
        // 将cover图片上传到阿里云
        if (blogUptDTO.getCover() != null)
            blog.setCover(OSSUtils.uploadFile(blogUptDTO.getCover()));
        if (!blogsService.updateById(blog)) {
            return Result.error().message("更新失败");
        }
        // 更新标签
        List<Integer> currentTagIds = blogTagService.getTagIdsByBlogId(id); // 获取当前博客的标签ID列表
        List<Integer> newTagIds = blogUptDTO.getTags(); // 获取更新后的标签ID列表
        // 找出被移除的标签
        List<Integer> removedTags = currentTagIds.stream()
                .filter(tagId -> !newTagIds.contains(tagId))
                .collect(Collectors.toList());
        if (!removedTags.isEmpty())
            blogTagService.removeTags(id, removedTags);
        // 找出被添加的标签
        List<Integer> addedTags = newTagIds.stream()
                .filter(tagId -> !currentTagIds.contains(tagId))
                .collect(Collectors.toList());
        if (!addedTags.isEmpty())
            blogTagService.saveTags(id, addedTags);
        return Result.ok().message("更新成功");

    }

    @DeleteMapping("/delete")
    public Result deleteBlog(@RequestParam Integer id) {
        if (!blogsService.removeById(id))
            return Result.error().message("删除失败");
        return Result.ok().message("删除成功");
    }

    @PostMapping("/publish")
    public Result publishBlog(@ModelAttribute BlogPubDTO blogPubDTO) throws OSSException, ClientException, IOException {
        Blogs blog = EntityUtils.toObj(blogPubDTO, Blogs::new);
        // 将cover图片上传到阿里云
        blog.setCover(OSSUtils.uploadFile(blogPubDTO.getCover()));
        if (!blogsService.save(blog)) {
            return Result.error().message("发布失败");
        }
        // 将标签id数组存入数据库
        blogTagService.saveTags(blog.getId(), blogPubDTO.getTags());
        return Result.ok().message("发布成功");
    }

    @PutMapping("ispublish/{id}")
    public Result putMethodName(@PathVariable Integer id, @RequestBody Boolean isPublish) {
        if (!blogsService.updatePublish(id, isPublish)) {
            return Result.error().message("发布失败");
        }
        return Result.ok().message("发布成功");
    }
}
