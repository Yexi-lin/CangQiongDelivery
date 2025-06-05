package com.sky.controller.admin;

import com.sky.dto.CategoryPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 分类管理
 * 菜品分类 套餐分类
 *
 * @Author:Yexi_lin
 * @Date: 2025/06/05 15:47
 * @Description:
 */
@Api(tags = "分类管理 CategoryController")
@RestController
@RequestMapping("/admin/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 分类的分页查询
     * @param dto
     * @return
     */
    @ApiOperation("分类分页查询接口 getCategoryPage")
    @GetMapping("/page")
    public Result<PageResult> getCategoryPage(CategoryPageQueryDTO dto) {
        PageResult pageResult = categoryService.getCategoryPage(dto);
        return Result.success(pageResult);
    }


}