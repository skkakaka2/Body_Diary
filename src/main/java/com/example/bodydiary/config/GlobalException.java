package com.example.bodydiary.config;

import com.example.bodydiary.common.Response;
import com.example.bodydiary.common.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * 全局异常处理器
 * 自动捕获并处理应用程序中的所有异常，返回统一的响应格式
 * 
 * 使用 @RestControllerAdvice 注解，Spring 会自动扫描并注册此异常处理器
 * 当 Controller 中抛出异常时，会根据异常类型自动调用对应的处理方法
 */
@Slf4j
@RestControllerAdvice
public class GlobalException {

    /**
     * 处理参数校验异常（@Valid 注解触发）
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response handleValidationException(MethodArgumentNotValidException e) {
        log.warn("参数校验失败: {}", e.getMessage());
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return Response.error(ResponseCode.VALIDATION_ERROR.getCode(), 
                "参数校验失败: " + errors.toString());
    }

    /**
     * 处理绑定异常（表单提交时）
     */
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response handleBindException(BindException e) {
        log.warn("数据绑定失败: {}", e.getMessage());
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return Response.error(ResponseCode.VALIDATION_ERROR.getCode(), 
                "数据绑定失败: " + errors.toString());
    }

    /**
     * 处理非法参数异常
     */
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response handleIllegalArgumentException(IllegalArgumentException e) {
        log.warn("非法参数: {}", e.getMessage());
        return Response.error(ResponseCode.BAD_REQUEST, e.getMessage());
    }

    /**
     * 处理数据库访问异常
     */
    @ExceptionHandler(DataAccessException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Response handleDataAccessException(DataAccessException e) {
        log.error("数据库访问异常: ", e);
        return Response.error(ResponseCode.DATABASE_ERROR, 
                "数据库操作失败，请稍后重试");
    }

    /**
     * 处理 SQL 异常
     */
    @ExceptionHandler(SQLException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Response handleSQLException(SQLException e) {
        log.error("SQL 执行异常: ", e);
        return Response.error(ResponseCode.DATABASE_ERROR, 
                "数据库操作失败: " + e.getMessage());
    }

    /**
     * 处理空指针异常
     */
    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Response handleNullPointerException(NullPointerException e) {
        log.error("空指针异常: ", e);
        return Response.error(ResponseCode.INTERNAL_SERVER_ERROR, 
                "系统内部错误，请联系管理员");
    }

    /**
     * 处理运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Response handleRuntimeException(RuntimeException e) {
        log.error("运行时异常: ", e);
        return Response.error(ResponseCode.ERROR, 
                "系统运行异常: " + e.getMessage());
    }

    /**
     * 处理所有其他异常（兜底处理）
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Response handleException(Exception e) {
        log.error("未知异常: ", e);
        return Response.error(ResponseCode.INTERNAL_SERVER_ERROR, 
                "系统异常，请联系管理员");
    }
}
