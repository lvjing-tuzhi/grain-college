package com.tuzhi.base_service.exceptionhandler;

import com.tuzhi.utilcommon.result.ResultCode;
import lombok.Data;

/**
 * @className: GuliException
 * @description: 自定义异常处理
 */

@Data
public class GuiException extends RuntimeException {

    private Integer code;

    private String msg;


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    private GuiException(Integer code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    private GuiException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public static GuiException from(ResultCode resultCode) {
        return new GuiException(resultCode.getCode(), resultCode.getMsg());
    }


    public static GuiException from(String msg) {
        return new GuiException(msg);
    }

    @Override
    public String toString() {
        return "GuiException{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }

}
