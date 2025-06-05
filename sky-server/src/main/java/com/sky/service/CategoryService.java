package com.sky.service;

import com.sky.dto.CategoryPageQueryDTO;
import com.sky.result.PageResult;

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
}
