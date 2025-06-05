package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author:Yexi_lin
 * @Date: 2025/06/05 16:08
 * @Description:
 */
@Mapper
public interface CategoryMapper {

    /**
     * 分类的分页查询
     * @param dto
     * @return
     */
    Page<Category> getCategoryPage(CategoryPageQueryDTO dto);
}
