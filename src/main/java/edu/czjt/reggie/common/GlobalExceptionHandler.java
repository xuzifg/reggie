package edu.czjt.reggie.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLIntegrityConstraintViolationException;

//create by jianing.an
/**
 * 全局异常处理器，用于统一处理应用程序中可能出现的异常，并向前端返回友好的提示信息。
 */
//通过使用 @ControllerAdvice 注解，标注它是一个全局异常处理器，并使用 annotations 属性限制它只针对 RestController 和 Controller 注解的控制器进行异常处理。
@ControllerAdvice(annotations = {RestController.class, Controller.class})
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 异常处理方法
     * @return
     */
    //用于处理 SQL 表约束异常，比如唯一键重复等。
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public R<String> exceptionHandler(SQLIntegrityConstraintViolationException ex){
        log.error(ex.getMessage());

        if(ex.getMessage().contains("Duplicate entry")){
            String[] split = ex.getMessage().split(" ");
            String msg = split[2] + "已存在";
            return R.error(msg);
        }

        return R.error("未知错误");
    }

    /**
     * 异常处理方法
     * @return
     */
    ////用于处理自定义异常。
    @ExceptionHandler(CustomException.class)
    //返回一个 R 对象，其中封装了异常处理的结果。
    public R<String> exceptionHandler(CustomException ex){
        log.error(ex.getMessage());

        return R.error(ex.getMessage());
    }

}

