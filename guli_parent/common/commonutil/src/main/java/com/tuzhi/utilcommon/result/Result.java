package com.tuzhi.utilcommon.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: guli_parent
 * @description: 返回的结果类
 * @author: 兔子
 * @create: 2022-03-08 13:05
 **/

@ApiModel("统一返回结果")
@Data
public class Result {
    @ApiModelProperty("是否成功")
    private Boolean success;

    @ApiModelProperty("返回码")
    private Integer code;

    @ApiModelProperty("返回信息")
    private String message;

    @ApiModelProperty("返回数据")
    private Map<String, Object> data = new HashMap<>();

    /**
     * 构造方法私有化
     */
    private Result() {
    }

    /**
     * 成功的静态方法
     *
     * @return Result
     */
    public static Result ok() {
        Result Result = new Result();
        Result.setSuccess(true);
        Result.setCode(ResultCode.SUCCESS.getCode());
        Result.setMessage(ResultCode.SUCCESS.getMsg());
        return Result;
    }

    /**
     * 失败的静态方法
     *
     * @return Result
     */
    public static Result error() {
        Result Result = new Result();
        Result.setSuccess(false);
        Result.setCode(ResultCode.ERROR.getCode());
        Result.setMessage(ResultCode.ERROR.getMsg());
        return Result;
    }


    public Result success(Boolean success) {
        this.setSuccess(success);
        return this;
    }

    public Result message(String message) {
        this.setMessage(message);
        return this;
    }

    public Result code(Integer code) {
        this.setCode(code);
        return this;
    }

    public Result data(String key, Object value) {
        this.data.put(key, value);
        return this;
    }


    public Result data(Map<String, Object> map) {
        this.setData(map);
        return this;
    }

    public Result codeAndMessage(Code resultCode) {
        this.setCode(resultCode.getCode());
        this.setMessage(resultCode.getMsg());
        return this;
    }
}
