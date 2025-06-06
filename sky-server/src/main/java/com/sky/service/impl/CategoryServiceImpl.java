package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.constant.ConditionConstant;
import com.sky.constant.MessageConstant;
import com.sky.constant.StatusConstant;
import com.sky.context.BaseContext;
import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.exception.DeletionNotAllowedException;
import com.sky.mapper.CategoryMapper;
import com.sky.mapper.DishMapper;
import com.sky.mapper.SetmealMapper;
import com.sky.result.PageResult;
import com.sky.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author:Yexi_lin
 * @Date: 2025/06/05 16:01
 * @Description:
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private DishMapper dishMapper;
    @Autowired
    private SetmealMapper setmealMapper;

    /**
     * 分类的分页查询
     *
     * @param dto
     * @return
     */
    @Override
    public PageResult getCategoryPage(CategoryPageQueryDTO dto) {
        //1.启用分页插件
        PageHelper.startPage(dto.getPage(), dto.getPageSize());
        //2.查询并获取Page对象
        Page<Category> page = categoryMapper.getCategoryPage(dto);
        //3.获取total和result并封装为PageResult返回
        long total = page.getTotal();
        List<Category> result = page.getResult();
        return new PageResult(total, result);
    }

    /**
     * 新增分类
     *
     * @param dto
     */
    @Override
    public void addCategory(CategoryDTO dto) {
        //将传入的DTO转换为Category对象
        Category category = new Category();
        BeanUtils.copyProperties(dto, category);
        //初始化分类状态 初始禁用
        category.setStatus(StatusConstant.DISABLE);
        //时间和操作人员数据填充改为AOP实现
        //初始化创建时间 修改时间
//        category.setCreateTime(LocalDateTime.now());
//        category.setUpdateTime(LocalDateTime.now());
        //初始化创建人 修改人
//        category.setCreateUser(BaseContext.getCurrentId());
//        category.setUpdateUser(BaseContext.getCurrentId());
        //调用mapper传参
        categoryMapper.addCategory(category);
    }

    /**
     * 修改分类
     *
     * @param dto
     */
    @Override
    public void updateCategory(CategoryDTO dto) {
        //将传入的DTO转换为Category对象
        Category category = new Category();
        BeanUtils.copyProperties(dto, category);
        //设置修改时间和修改人
        //已改为AOP实现
//        category.setUpdateTime(LocalDateTime.now());
//        category.setUpdateUser(BaseContext.getCurrentId());
        categoryMapper.updateCategory(category);
    }

    /**
     * 设置分类启用状态
     *
     * @param status
     * @param id
     */
    @Override
    public void setCategoryEnableStatus(Integer status, Long id) {
        //封装为Category 以便调用修改分类的接口
        Category category = new Category();
        category.setStatus(status);
        category.setId(id);
        categoryMapper.updateCategory(category);
    }

    /**
     * 根据Id删除分类
     *
     * @param id
     */
    @Override
    public void deleteCategoryById(Long id) {
        //删除前需要检查是否当前分类下还有其他菜品 如有 不予删除 通过抛出异常中断操作 并 使用全局异常处理器返回提示信息
        //先获取当前分类是菜品分类还是套餐分类
        if (categoryMapper.getCategoryTypeById(id).equals(ConditionConstant.DISH_OR_SETMEAL)) {
            //判断是否已经关联菜品
            if (dishMapper.hasRelatedByDish(id)) {
                //有关联菜品时抛出 不予删除 异常
                throw new DeletionNotAllowedException(MessageConstant.CATEGORY_BE_RELATED_BY_DISH);
            }
        } else {
            //判断是否已经关联套餐
            if (setmealMapper.hasRelatedBySetmeal(id)){
                //有关联套餐时抛出 不予删除 异常
                throw new DeletionNotAllowedException(MessageConstant.CATEGORY_BE_RELATED_BY_SETMEAL);
            }
        }
        //检查通过后执行删除方法
        categoryMapper.deleteCategoryById(id);
    }

    /**
     * 根据类型查询分类 且只返回已启用的分类
     *
     * @param type
     * @return
     */
    @Override
    public List<Category> getCategoryListByType(Integer type) {
        List<Category> list = categoryMapper.getCategoryListByType(type);
        return list;
    }
}