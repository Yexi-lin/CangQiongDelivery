package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.enumeration.OperationType;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;

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
    @AutoFill(OperationType.INSERT)
    @Insert("insert category (name, type, sort, status, create_time, update_time, create_user, update_user)" +
            "values(#{name}, #{type}, #{sort}, #{status}, #{createTime}, #{updateTime}, #{createUser}, #{updateUser})")
    void addCategory(Category category);

    /**
     * 修改分类
     * @param category
     */
    @AutoFill(OperationType.UPDATE)
    void updateCategory(Category category);

    /**
     * 根据Id删除分类
     * @param id
     */
    @Delete("delete from category where id = #{id}")
    void deleteCategoryById(Long id);

    /**
     * 根据类型查询分类 且仅显示启用的分类
     * @param type
     * @return
     */
    List<Category> getCategoryListByType(Integer type);

    /**
     * 根据分类id查询分类类型
     * @param id
     * @return  1 菜品分类 2 套餐分类
     */
    @Select("select type from category where id = #{id}")
    Integer getCategoryTypeById(Long id);
}
