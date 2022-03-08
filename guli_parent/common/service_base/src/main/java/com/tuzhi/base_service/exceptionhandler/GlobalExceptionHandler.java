package com.tuzhi.base_service.exceptionhandler;

import com.tuzhi.utilcommon.result.Result;
import com.tuzhi.utilcommon.result.ResultCode;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @program: guli_parent
 * @description:异常处理类
 * @author: 兔子
 * @create: 2022-03-08 22:27
 **/

@ControllerAdvice
public class GlobalExceptionHandler {

//    指定出现什么异常执行下面这个方法
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e) {
        e.printStackTrace();
        return Result.error().code(ResultCode.ERROR.getCode()).message("请求失败");
    }
}
