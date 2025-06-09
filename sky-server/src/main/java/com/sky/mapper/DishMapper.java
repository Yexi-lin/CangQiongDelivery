package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.enumeration.OperationType;
import com.sky.vo.DishVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @Author:Yexi_lin
 * @Date: 2025/06/06 00:07
 * @Description:
 */
@Mapper
public interface DishMapper {

    /**
     * 传入菜品分类id 根据关联字段 category_id判断是否存在关联关系
     *
     * @param id
     * @return true 查询的菜品分类已关联菜品 false 未关联
     */
    @Select("select exists(select null from dish where category_id = #{id})")
    boolean hasRelatedByDish(Long id);

    /**
     * 新增菜品
     *
     * @param dish
     */
    @AutoFill(OperationType.INSERT)
    void addDish(Dish dish);

    /**
     * 菜品分页查询
     * 同时查询菜品信息和菜品关联的分类名称
     * @param dishPageQueryDTO
     * @return
     */
    Page<DishVO> getDishPage(DishPageQueryDTO dishPageQueryDTO);
}
