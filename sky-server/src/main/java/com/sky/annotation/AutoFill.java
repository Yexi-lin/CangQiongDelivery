package com.sky.annotation;

import com.sky.enumeration.OperationType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author:Yexi_lin
 * @Date: 2025/06/06 09:07
 * @Description:
 */
//设置注解作用位置 设置在Service层或Mapper层都可以 本项目中设置在Mapper层
@Target(ElementType.METHOD)
//设置注解存货周期(程序运行时可用)
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoFill {
    //设置注解属性 用于标识新增方法(INSERT)或修改方法(UPDATE)
    OperationType value();
}
