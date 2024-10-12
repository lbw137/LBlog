package com.lyb.lyblog.controller.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lyb.lyblog.model.VO.BlogArcVO;
import com.lyb.lyblog.model.VO.BlogDetailVO;
import com.lyb.lyblog.model.VO.BlogListVO;
import com.lyb.lyblog.service.BlogsService;
import com.lyb.lyblog.utils.Result;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RestController
@RequestMapping("/client/blogs")
public class BlogsController {
    @Autowired
    private BlogsService blogsService;

    @GetMapping("/home")
    public Result getBlogsHome() {
        List<BlogListVO> bloglist = blogsService.getBlogsHome();
        return Result.ok().data("blogs", bloglist);
    }

    @GetMapping("/tag")
    public Result getBlogsByTagId(@RequestParam Integer id) {
        List<BlogListVO> bloglist = blogsService.getBlogsByTagId(id);
        return Result.ok().data("blogs", bloglist);
    }

    @GetMapping("/category")
    public Result getBlogsByCatId(@RequestParam Integer id) {
        List<BlogListVO> bloglist = blogsService.getBlogsByCatId(id);
        return Result.ok().data("blogs", bloglist);
    }

    @GetMapping("/detail")
    public Result getBlogById(@RequestParam Integer id) {
        BlogDetailVO blog = blogsService.getBlogById(id);
        return Result.ok().data("blog", blog);
    }

    @GetMapping("/archivist")
    public Result getBlogArchivist() {
        long count = blogsService.count();
        List<BlogArcVO> blog = blogsService.getBlogArchivist();
        return Result.ok().data("count", count).data("blogMap", blog);
    }
}
