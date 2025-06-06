package com.sky.aspect;

import com.sky.annotation.AutoFill;
import com.sky.constant.AutoFillConstant;
import com.sky.context.BaseContext;
import com.sky.enumeration.OperationType;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;

/**
 * 实体类的新增及修改 时间操作人属性的自动装配
 * @Author:Yexi_lin
 * @Date: 2025/06/06 09:15
 * @Description:
 */
//声明为切面类
@Aspect
//导入IOC容器管理
@Component
public class AutoFillAspect {

    //创建前置声明 环绕声明也可以
    @Before("@annotation(com.sky.annotation.AutoFill)")
    public void autoFill(JoinPoint joinPoint) {
        System.out.println("对象信息自动装配...");
        //获取当前访问到的方法 及参数对象
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        //参数对象
        Object[] args = joinPoint.getArgs();
        Object entity = args[0];
        //通过注解的type类型判断方法类型
        //获取注解类型
        OperationType value = method.getAnnotation(AutoFill.class).value();
        //获取参数对象类型的字节码 以便调用getMethod方法 从而调用set方法进行装配
        Class entityClass = entity.getClass();

        LocalDateTime now = LocalDateTime.now();
        Long currentId = BaseContext.getCurrentId();

        if (value.equals(OperationType.INSERT)) {
            try {
                Method setCreatTime = entityClass.getMethod(AutoFillConstant.SET_CREATE_TIME, LocalDateTime.class);
                setCreatTime.invoke(entity, now);
                Method setCreateUser = entityClass.getMethod(AutoFillConstant.SET_CREATE_USER, Long.class);
                setCreateUser.invoke(entity, currentId);
                Method setUpdateTime = entityClass.getMethod(AutoFillConstant.SET_UPDATE_TIME, LocalDateTime.class);
                setUpdateTime.invoke(entity, now);
                Method setUpdateUser = entityClass.getMethod(AutoFillConstant.SET_UPDATE_USER, Long.class);
                setUpdateUser.invoke(entity, currentId);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                Method setUpdateTime = entityClass.getMethod(AutoFillConstant.SET_UPDATE_TIME, LocalDateTime.class);
                setUpdateTime.invoke(entity, now);
                Method setUpdateUser = entityClass.getMethod(AutoFillConstant.SET_UPDATE_USER, Long.class);
                setUpdateUser.invoke(entity, currentId);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}