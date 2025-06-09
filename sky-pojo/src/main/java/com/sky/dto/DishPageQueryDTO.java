package com.sky.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(description = "分类分页查询的参数模型")
public class DishPageQueryDTO implements Serializable {
    @ApiModelProperty("页码")
    private int page;
    @ApiModelProperty("每页显示记录数")
    private int pageSize;
    @ApiModelProperty("分类名称")
    private String name;

    //分类id
    @ApiModelProperty("分类id")
    private Integer categoryId;

    //状态 0表示禁用 1表示启用
    @ApiModelProperty("分类状态 0表示禁用 1表示启用")
    private Integer status;

}
