package com.lyb.lyblog.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lyb.lyblog.model.PO.Categories;
import com.lyb.lyblog.model.PO.Tags;
import com.lyb.lyblog.service.CategoriesService;
import com.lyb.lyblog.service.TagsService;
import com.lyb.lyblog.utils.Result;

@RestController
@RequestMapping("/admin")
public class SiteInfoAdController {
    @Autowired
    private TagsService tagsService;
    @Autowired
    private CategoriesService categoriesService;

    // 标签管理
    @DeleteMapping("/tags/delete")
    public Result deleteTag(@RequestParam Integer id) {
        if (tagsService.removeById(id))
            return Result.ok().message("删除成功");
        return Result.error().message("删除失败");
    }

    @PutMapping("/tags/update")
    public Result updateTag(@RequestBody Tags tag) {
        if (tagsService.updateById(tag))
            return Result.ok().message("修改成功");
        return Result.error().message("修改失败");
    }

    @PostMapping("/tags/pub")
    public Result publishTag(@RequestBody Tags tag) {
        if (tagsService.save(tag))
            return Result.ok().message("发布成功");
        return Result.error().message("发布失败");
    }

    // 分类管理
    @DeleteMapping("/category/delete")
    public Result deleteCategory(@RequestParam Integer id) {
        if (categoriesService.removeById(id))
            return Result.ok().message("删除成功");
        return Result.error().message("删除失败");
    }

    @PutMapping("/category/update")
    public Result updateCategory(@RequestBody Categories category) {
        if (categoriesService.updateById(category))
            return Result.ok().message("修改成功");
        return Result.error().message("修改失败");
    }

    @PostMapping("/category/pub")
    public Result publishCategory(@RequestBody Categories category) {
        if (categoriesService.save(category))
            return Result.ok().message("发布成功");
        return Result.error().message("发布失败");
    }
}
