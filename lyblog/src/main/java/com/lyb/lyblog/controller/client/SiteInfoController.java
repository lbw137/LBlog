package com.lyb.lyblog.controller.client;

import org.springframework.web.bind.annotation.RestController;

import com.lyb.lyblog.model.PO.Categories;
import com.lyb.lyblog.model.PO.Tags;
import com.lyb.lyblog.service.CategoriesService;
import com.lyb.lyblog.service.TagsService;
import com.lyb.lyblog.utils.Result;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class SiteInfoController {
    @Autowired
    private TagsService tagsService;
    @Autowired
    private CategoriesService categoriesService;

    @GetMapping("/tags")
    public Result getTags() {
        List<Tags> tagList = tagsService.list();
        return Result.ok().data("tags", tagList);
    }

    @GetMapping("/categories")
    public Result getCategories() {
        List<Categories> categoryList = categoriesService.list();
        return Result.ok().data("categories", categoryList);
    }
}
