package com.sky.service;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.result.PageResult;

/**
 * @Author:Yexi_lin
 * @Date: 2025/06/06 11:32
 * @Description:
 */
public interface DishService {


    /**
     * 新增菜品
     *
     * @param dto
     */
    void addDish(DishDTO dto);

    /**
     * 菜品分页查询
     *
     * @param dishPageQueryDTO
     * @return
     */
    PageResult getDishPage(DishPageQueryDTO dishPageQueryDTO);
}
