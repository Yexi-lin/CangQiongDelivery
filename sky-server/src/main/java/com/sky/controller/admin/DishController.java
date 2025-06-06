package com.sky.controller.admin;

import com.sky.service.DishService;
import com.sky.dto.DishDTO;
import com.sky.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author:Yexi_lin
 * @Date: 2025/06/06 11:19
 * @Description:
 */
@Api(tags = "菜品管理 DishController")
@RestController
@RequestMapping("/admin/dish")
public class DishController {

    @Autowired
    private DishService dishService;

    /**
     * 新增菜品
     * @param dto
     * @return
     */

    @ApiOperation("新增菜品 addDish")
    @PostMapping()
    public Result addDish(@RequestBody DishDTO dto){
        dishService.addDish(dto);
        return Result.success();
    }

}