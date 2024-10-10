package com.lyb.lyblog.service;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyb.lyblog.mapper.CategoriesMapper;
import com.lyb.lyblog.model.PO.Categories;

@Service
public class CategoriesService extends ServiceImpl<CategoriesMapper, Categories> {
    public Categories getCategoryById(Integer id) {
        return this.getById(id);
    }
}
