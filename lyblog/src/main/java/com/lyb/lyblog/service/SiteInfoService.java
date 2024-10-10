package com.lyb.lyblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lyb.lyblog.model.VO.SiteInfoVO;

@Service
public class SiteInfoService {
    @Autowired
    private BlogsService blogsService;
    @Autowired
    private TagsService tagsService;
    @Autowired
    private CategoriesService categoriesService;

    public SiteInfoVO getSiteInfo() {
        SiteInfoVO siteInfoVO = new SiteInfoVO();
        siteInfoVO.setBlogs(blogsService.list());
        siteInfoVO.setTags(tagsService.list());
        siteInfoVO.setCategories(categoriesService.list());
        return siteInfoVO;
    }

}
