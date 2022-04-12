package com.tuzhi.base_service.exceptionhandler;

import com.tuzhi.utilcommon.result.Result;
import com.tuzhi.utilcommon.result.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

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
    /**
     * 自定义异常处理
     *
     * @param e
     * @return ResultVo
     */
    @ExceptionHandler(GuiException.class)
    public Result error(GuiException e) {
        log.error("异常信息：{}", e);
        return Result.error().code(e.getCode()).message(e.getMsg());
    }

    @ExceptionHandler(IOException.class)
    public Result error(IOException e) {
        log.error("异常信息", e);
        throw GuiException.from(ResultCode.UPLOAD_FILE_ERROR);
    }
}
