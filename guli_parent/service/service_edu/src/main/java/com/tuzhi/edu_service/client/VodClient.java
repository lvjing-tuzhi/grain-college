package com.tuzhi.edu_service.client;

import com.tuzhi.utilcommon.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @program: guli_parent
 * @description: 调用远程的Vod微服务
 * @author: 兔子
 * @create: 2022-04-16 15:24
 **/

//name=服务名称
@FeignClient(name = "service-vod", fallback = VodFileDegradeFeignClient.class)
@Component
public interface VodClient {

    //定义调用的方法路径，方法路径已经要写全
    //根据视频id删除阿里云视频
    //@PathVariable注解一定要指定参数名称，否则出错
    //删除单个视频
    @DeleteMapping("/eduvod/{videoId}")
    Result deleteVideo(@PathVariable("videoId") String videoId);

    //删除多个视频
    @DeleteMapping("/eduvod/deleteBatch")
    public Result deleteVideoBatch(@RequestParam("list") List<String> list);

}
