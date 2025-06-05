package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.mapper.CategoryMapper;
import com.sky.result.PageResult;
import com.sky.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author:Yexi_lin
 * @Date: 2025/06/05 16:01
 * @Description:
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 分类的分页查询
     * @param dto
     * @return
     */
    @Override
    public PageResult getCategoryPage(CategoryPageQueryDTO dto) {
        //1.启用分页插件
        PageHelper.startPage(dto.getPage(), dto.getPageSize());
        //2.查询并获取Page对象
        Page<Category> page = categoryMapper.getCategoryPage(dto);
        //3.获取total和result并封装为PageResult返回
        long total = page.getTotal();
        List<Category> result = page.getResult();
        return new PageResult(total, result);
    }
}