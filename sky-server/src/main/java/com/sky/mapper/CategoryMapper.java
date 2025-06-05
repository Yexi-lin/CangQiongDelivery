package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import org.apache.ibatis.annotations.Insert;
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

    /**
     * 新增分类
     * @param category
     */
    @Insert("insert category (name, type, sort, status, create_time, update_time, create_user, update_user)" +
            "values(#{name}, #{type}, #{sort}, #{status}, #{createTime}, #{updateTime}, #{createUser}, #{updateUser})")
    void addCategory(Category category);

    /**
     * 修改分类
     * @param category
     */
    void updateCategory(Category category);
}
