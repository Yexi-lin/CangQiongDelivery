package com.sky.service;

import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.result.PageResult;

import java.util.List;

/**
 * @Author:Yexi_lin
 * @Date: 2025/06/05 16:01
 * @Description:
 */
public interface CategoryService {

    /**
     * 分类的分页查询
     * @param dto
     * @return
     */
    PageResult getCategoryPage(CategoryPageQueryDTO dto);

    /**
     * 新增分类
     * @param dto
     */
    void addCategory(CategoryDTO dto);

    /**
     * 修改分类
     * @param dto
     */
    void updateCategory(CategoryDTO dto);

    /**
     * 设置分类启用状态
     * @param status
     * @param id
     */
    void setCategoryEnableStatus(Integer status, Long id);

    /**
     * 根据Id删除分类
     * @param id
     */
    void deleteCategoryById(Long id);

    /**
     * 根据类型查询分类 且只返回已启用的分类
     * @param type
     * @return
     */
    List<Category> getCategoryListByType(Integer type);
}
