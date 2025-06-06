package com.sky.dto;

import com.sky.entity.DishFlavor;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@ApiModel(description = "新增和修改菜品的参数模型")
public class DishDTO implements Serializable {

    @ApiModelProperty("菜品id")
    private Long id;
    //菜品名称
    @ApiModelProperty("菜品名称")
    private String name;
    //菜品分类id
    @ApiModelProperty("菜品分类id")
    private Long categoryId;
    //菜品价格
    @ApiModelProperty("菜品价格")
    private BigDecimal price;
    //图片
    @ApiModelProperty("菜品图片信息")
    private String image;
    //描述信息
    @ApiModelProperty("菜品描述信息")
    private String description;
    //0 停售 1 起售
    @ApiModelProperty("菜品起售状态 0 停售 1 起售")
    private Integer status;
    //口味
    @ApiModelProperty("菜品口味")
    private List<DishFlavor> flavors = new ArrayList<>();

}
