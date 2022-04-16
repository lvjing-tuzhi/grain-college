package com.tuzhi.edu_service.client;


import com.tuzhi.base_service.exceptionhandler.GuiException;
import com.tuzhi.utilcommon.result.Result;
import com.tuzhi.utilcommon.result.ResultCode;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VodFileDegradeFeignClient implements VodClient {

    @Override
    public Result deleteVideo(String videoId) {
        System.out.println("执行了熔断删除视频出错了");

        return Result.error().message("删除视频出错了");
    }

    @Override
    public Result deleteVideoBatch(List<String> list) {
        System.out.println("执行了熔断删除多个视频出错了");
        return Result.error().message("删除多个视频出错了");
    }
}
