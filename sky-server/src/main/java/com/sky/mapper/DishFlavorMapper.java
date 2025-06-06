package com.sky.mapper;

import com.sky.annotation.AutoFill;
import com.sky.entity.DishFlavor;
import com.sky.enumeration.OperationType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author:Yexi_lin
 * @Date: 2025/06/06 12:09
 * @Description:
 */
@Mapper
public interface DishFlavorMapper {

    /**
     * 批量新增菜品口味
     * @param flavors
     */
    void addDishFlavors(List<DishFlavor> flavors);
}
