package com.sky.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(description = "新增和修改分类的传输数据模型")
public class CategoryDTO implements Serializable {

    //主键
    @ApiModelProperty("分类id")
    private Long id;

    //类型 1 菜品分类 2 套餐分类
    @ApiModelProperty("类型 1 菜品分类 2 套餐分类")
    private Integer type;

    //分类名称
    @ApiModelProperty("分类名称")
    private String name;

    //排序
    @ApiModelProperty("分类排序权重")
    private Integer sort;

}
