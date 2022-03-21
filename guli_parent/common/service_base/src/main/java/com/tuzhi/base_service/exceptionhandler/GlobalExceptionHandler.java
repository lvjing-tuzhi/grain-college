package com.tuzhi.base_service.exceptionhandler;

import com.tuzhi.utilcommon.result.Result;
import com.tuzhi.utilcommon.result.ResultCode;
import lombok.extern.slf4j.Slf4j;
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
//把异常写入日志文件中
@Slf4j
public class GlobalExceptionHandler {

//    指定出现什么异常执行下面这个方法
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e) {
        e.printStackTrace();
        log.error(e.getMessage());
        return Result.error().code(ResultCode.ERROR.getCode()).message("请求失败");
    }
}
