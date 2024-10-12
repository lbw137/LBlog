package com.lyb.lyblog.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lyb.lyblog.model.VO.BlogListAdVO;
import com.lyb.lyblog.service.BlogsService;
import com.lyb.lyblog.utils.Result;
import java.util.List;

@RestController
@RequestMapping("/admin/blogs")
public class BlogsAdController {
    @Autowired
    private BlogsService blogsService;

    @GetMapping("/list")
    public Result getBlogListAd() {
        List<BlogListAdVO> blogs = blogsService.getBlogListAd();
        return Result.ok().data("blogs", blogs);
    }
}
