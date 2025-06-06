package com.sky.controller.admin;

import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    /**
     * 设置分类启用状态
     * @param status
     * @param id
     * @return
     */
    @ApiOperation("设置分类启用状态 setCategoryEnableStatus")
    @PostMapping("/status/{status}")
    public Result setCategoryEnableStatus(@PathVariable
                                          @ApiParam("启用状态 1 启用 0 禁用")
                                          Integer status,
                                          @RequestParam
                                          @ApiParam("分类id")
                                          Long id){
        categoryService.setCategoryEnableStatus(status, id);
        return Result.success();
    }

    /**
     * 根据Id删除分类
     * @param id
     * @return
     */
    @ApiOperation("根据Id删除分类 deleteCategoryById")
    @DeleteMapping()
    public Result deleteCategoryById(@RequestParam
                                     @ApiParam("分类id")
                                     Long id){
        categoryService.deleteCategoryById(id);
        return Result.success();
    }

    /**
     * 根据类型查询分类 且只返回已启用的分类
     * @param type
     * @return
     */
    @ApiOperation("根据类型查询分类 getCategoryListByType")
    @GetMapping("/list")
    public Result<List<Category>> getCategoryListByType(@RequestParam
                                                        @ApiParam("分类类型 1 菜品分类 2 套餐分类")
                                                        Integer type){
        List<Category> list = categoryService.getCategoryListByType(type);
        return Result.success(list);
    }
}