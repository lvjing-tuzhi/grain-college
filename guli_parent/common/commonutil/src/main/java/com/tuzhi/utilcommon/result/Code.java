package com.tuzhi.utilcommon.result;

/**
 * @program: guli_parent
 * @description:
 * @author: 兔子
 * @create: 2022-03-08 13:12
 **/

public interface Code {
    /**
     *  获取响应码
     * @return 响应码
     */
    Integer getCode();


    /**
     * 获取响应信息
     *
     * @return 响应信息
     */
    String getMsg();
}
