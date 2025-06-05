package com.sky.handler;

import com.sky.constant.ConditionConstant;
import com.sky.constant.MessageConstant;
import com.sky.exception.BaseException;
import com.sky.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * 全局异常处理器，处理项目中抛出的业务异常
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 捕获业务异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler
    public Result exceptionHandler(BaseException ex) {
        log.error("异常信息：{}", ex.getMessage());
        return Result.error(ex.getMessage());
    }

    // 数据库完整性约束异常 唯一 外键 非空 条件
    @ExceptionHandler
    public Result exceptionHandler(SQLIntegrityConstraintViolationException ex) {
        String exMessage = ex.getMessage();
        log.error("异常信息：{}", exMessage);
        String[] msgSplit = exMessage.split(" ");
        if (msgSplit[0].equals(ConditionConstant.WEATHER_DUPLICATE)) {
            if (msgSplit[msgSplit.length - 1].equals(ConditionConstant.WEATHER_USERNAME_KEY_IN_EMPLOYEE)) {
                return Result.error(MessageConstant.USERNAME + msgSplit[2] + MessageConstant.DUPLICATE_ENTRIE);
            }
        }
        return Result.error(MessageConstant.UNKNOWN_ERROR);
    }

}
