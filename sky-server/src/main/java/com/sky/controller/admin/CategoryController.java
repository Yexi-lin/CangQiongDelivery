package com.sky.controller.admin;

import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 新增分类
     * @param dto
     * @return
     */
    @ApiOperation("新增分类 addCategory")
    @PostMapping()
    public Result addCategory(@RequestBody CategoryDTO dto){
        categoryService.addCategory(dto);
        return Result.success();
    }

    /**
     * 修改分类
     * @param dto
     * @return
     */
    @ApiOperation("修改分类 updateCategory")
    @PutMapping()
    public Result updateCategory(@RequestBody CategoryDTO dto){
           categoryService.updateCategory(dto);
           return Result.success();
    }
}