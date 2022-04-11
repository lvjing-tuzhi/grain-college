package com.tuzhi.edu_service.controller;

import com.tuzhi.edu_service.pojo.vo.CourseInfoForm;
import com.tuzhi.edu_service.service.EduCourseService;
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

@Api(tags = "课程接口")
@RestController
@RequestMapping("/eduservice/eduCourse")
@CrossOrigin
public class EduCourseController {

    @Autowired
    EduCourseService eduCourseService;

    @ApiOperation("添加课程接口")
    @PostMapping("/addCourse")
    public Result addCourse(@RequestBody CourseInfoForm courseInfoForm) {
        String courseId = eduCourseService.saveCourse(courseInfoForm);
        return Result.ok().data("courseId", courseId);
    }

    @ApiOperation("根据课程id获得课程")
    @GetMapping("/getById/{courseId}")
    public Result getCourseById(@PathVariable String courseId) {
        CourseInfoForm courseInfoFormList = eduCourseService.getCourseById(courseId);
        return Result.ok().data("list", courseInfoFormList);
    }

    @ApiOperation("修改课程")
    @PostMapping("/updateCourse")
    public Result updateCourse(@RequestBody CourseInfoForm courseInfoForm) {
        eduCourseService.updateCourse(courseInfoForm);
        return Result.ok();
    }
}
