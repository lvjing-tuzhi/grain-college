package com.tuzhi.edu_service.controller;

import com.tuzhi.edu_service.pojo.EduVideo;
import com.tuzhi.edu_service.service.EduVideoService;
import com.tuzhi.utilcommon.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author tuzhi
 * @since 2022-04-08
 */
@Api(tags = "小节接口")
@RestController
@RequestMapping("/eduservice/eduVideo")
@CrossOrigin
public class EduVideoController {

    @Autowired
    EduVideoService videoService;

    @ApiOperation("添加小节")
    @PostMapping("/addVideo")
    public Result addVideo(@RequestBody EduVideo eduVideo) {
        videoService.save(eduVideo);
        return Result.ok();
    }

    @ApiOperation("修改小节")
    @PostMapping("/updateVideo")
    public Result updateVideo(@RequestBody EduVideo eduVideo) {
        videoService.updateById(eduVideo);
        return Result.ok();
    }

    @ApiOperation("删除小节")
    @DeleteMapping("/{id}")
    public Result deleteVideo(@PathVariable String id) {
        videoService.removeById(id);
        return Result.ok();
    }

    @ApiOperation("根据小节id查询小节")
    @GetMapping("/getVideoById/{id}")
    public Result getViedeoById(@PathVariable String id) {
        EduVideo video = videoService.getById(id);
        return Result.ok().data("video", video);
    }
}
