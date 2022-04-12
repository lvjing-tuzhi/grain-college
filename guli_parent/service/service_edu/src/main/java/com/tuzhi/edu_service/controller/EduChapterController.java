package com.tuzhi.edu_service.controller;

import com.tuzhi.edu_service.pojo.EduChapter;
import com.tuzhi.edu_service.pojo.chapter.ChapterVo;
import com.tuzhi.edu_service.service.EduChapterService;
import com.tuzhi.utilcommon.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author tuzhi
 * @since 2022-04-08
 */
@Api(tags = "章节接口")
@RestController
@RequestMapping("/eduservice/eduChapter")
@CrossOrigin
public class EduChapterController {

    @Autowired
    EduChapterService chapterService;

    @ApiOperation("根据课程ID章节")
    @GetMapping("/getChapterThree/{courseId}")
    public Result getChapterTree(@PathVariable String courseId) {
        List<ChapterVo> chapterVoList = chapterService.getChapter(courseId);
        return Result.ok().data("list", chapterVoList);
    }

    @ApiOperation("添加章节")
    @PostMapping("/addChapter")
    public Result addChapter(@RequestBody EduChapter eduChapter) {
        chapterService.save(eduChapter);
        return Result.ok();
    }

    @ApiOperation("修改章节")
    @PostMapping("/updateChapter")
    public Result updateChapter(@RequestBody EduChapter eduChapter) {
        chapterService.updateById(eduChapter);
        return Result.ok();
    }

    @ApiOperation("删除章节")
    @DeleteMapping("/{id}")
    public Result deleteChapter(@PathVariable String id) {
        chapterService.deleteChapter(id);
        return Result.ok();
    }

    @ApiOperation("根据章节ID查询章节")
    @GetMapping("/chapter/{id}")
    public Result getChapterById(@PathVariable String id) {
        EduChapter eduChapter = chapterService.getById(id);
        return Result.ok().data("chapter", eduChapter);
    }


}
