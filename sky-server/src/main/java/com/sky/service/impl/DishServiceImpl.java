package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.entity.DishFlavor;
import com.sky.mapper.DishFlavorMapper;
import com.sky.mapper.DishMapper;
import com.sky.result.PageResult;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author:Yexi_lin
 * @Date: 2025/06/06 11:35
 * @Description:
 */
@Service
public class DishServiceImpl implements DishService {

    @Autowired
    private DishMapper dishMapper;
    @Autowired
    private DishFlavorMapper dishFlavorMapper;


    /**
     * 新增菜品
     * @param dto
     */
    @Override
    public void addDish(DishDTO dto) {
        //1.从dto中转换dish对象 并新增菜品
        Dish dish = new Dish();
        BeanUtils.copyProperties(dto, dish);
        dishMapper.addDish(dish);

        //2.新增菜品风味 并关联菜品id
        //获取菜品的主键值
        Long dishId = dish.getId();
        List<DishFlavor> flavors = dto.getFlavors();
        //判断菜品是否添加了口味
        if(flavors != null && !flavors.isEmpty()){
//            for (DishFlavor f : flavors) {
//                f.setDishId(dishId);
//            }
            flavors.forEach(dishFlavor -> dishFlavor.setDishId(dishId));
            //批量新增菜品口味
            dishFlavorMapper.addDishFlavors(flavors);
        }
    }

    @Override
    public PageResult getDishPage(DishPageQueryDTO dishPageQueryDTO) {
        //1.启用PageHelper
        PageHelper.startPage(dishPageQueryDTO.getPage(), dishPageQueryDTO.getPageSize());
        //2.查询并将结果封装为Page
        Page<DishVO> page = dishMapper.getDishPage(dishPageQueryDTO);//后绪步骤实现
        //将page中的表和总数封装为PageResult并返回
        List<DishVO> result = page.getResult();
        long total = page.getTotal();
        return new PageResult(total, result);
    }
}