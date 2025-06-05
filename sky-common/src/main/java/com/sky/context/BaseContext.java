package com.sky.context;

/**
 * 一个操作ThreadLocal的工具类
 * ThreadLocal作为一个公共容器,对每个线程独立
 * 可以在各个程序中进行公共数据(例如token和当前用户信息)的获取的调用
 * 设置 获取 删除
 */
public class BaseContext {

    public static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    public static void setCurrentId(Long id) {
        threadLocal.set(id);
    }

    public static Long getCurrentId() {
        return threadLocal.get();
    }

    public static void removeCurrentId() {
        threadLocal.remove();
    }

}
