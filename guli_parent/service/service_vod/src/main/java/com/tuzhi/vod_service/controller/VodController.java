package com.tuzhi.vod_service.controller;

import com.tuzhi.utilcommon.result.Result;
import com.tuzhi.vod_service.service.VodService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @program: guli_parent
 * @description: 阿里云视频接口
 * @author: 兔子
 * @create: 2022-04-14 20:56
 **/

@Api(tags = "阿里云视频接口")
@RestController
@RequestMapping("/eduvod")
@CrossOrigin
public class VodController {

    @Autowired
    private VodService vodService;

    @ApiOperation("上传视频")
    @PostMapping("/uploadVideo")
    public Result uploadVideo(MultipartFile file) {
        String videoId = vodService.upload(file);
        return Result.ok().data("videoId",videoId);
    }

    @ApiOperation("删除视频")
    @DeleteMapping("/{videoId}")
    public Result deleteVideo(@PathVariable String videoId) {
        System.out.println("===============controller=============");
        System.out.println(videoId);
        vodService.delete(videoId);
        return Result.ok();
    }

    @ApiOperation("获取视频地址")
    @GetMapping("/getVideoAdress/{videoId}")
    public Result getVideoAdress(@PathVariable String videoId) {
        String playInfo = vodService.getPlayInfo(videoId);
        return Result.ok().data("playInfo", playInfo);
    }

    @ApiOperation("获取视频播放凭证")
    @GetMapping("/getVideoAuth/{videoId}")
    public Result getVideoAuth(@PathVariable String videoId) {
        String videoPlayAuth = vodService.getVideoPlayAuth(videoId);
        return Result.ok().data("videoPlayAuth", videoPlayAuth);
    }

}
