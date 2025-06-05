package com.sky.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @Author:Yexi_lin
 * @Date: 2025/06/06 00:07
 * @Description:
 */
@Mapper
public interface SetmealMapper {


    /**
     * 传入套餐分类id 根据关联字段 category_id判断是否存在关联关系
     * @param id
     * @return true 查询的套餐分类已关联套餐 false 未关联
     */
    @Select("select exists(select null from setmeal where category_id = #{id})")
    boolean hasRelatedBySetmeal(Long id);
}
