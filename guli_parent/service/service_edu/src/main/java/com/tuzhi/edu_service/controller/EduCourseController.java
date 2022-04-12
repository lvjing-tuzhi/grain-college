package com.tuzhi.edu_service.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tuzhi.edu_service.pojo.EduCourse;
import com.tuzhi.edu_service.pojo.EduTeacher;
import com.tuzhi.edu_service.pojo.vo.CourseInfoForm;
import com.tuzhi.edu_service.pojo.vo.CoursePublishVo;
import com.tuzhi.edu_service.pojo.vo.CourseQuery;
import com.tuzhi.edu_service.pojo.vo.TeacherQuery;
import com.tuzhi.edu_service.service.EduCourseService;
import com.tuzhi.utilcommon.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
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

    @ApiOperation("删除课程")
    @DeleteMapping("/{courseId}")
    public Result removeCourseById(@PathVariable String courseId) {
        eduCourseService.removeCourseById(courseId);
        return Result.ok();
    }

    @ApiOperation("多条件分页查询")
    @PostMapping("/pageCourseCondition/{current}/{limit}")
    @ApiImplicitParams({@ApiImplicitParam(name = "current", value = "当前页"), @ApiImplicitParam(name = "limit", value = "条数")})
    public Result pageTeacherConditoin(@PathVariable Integer current, @PathVariable Integer limit
            , @RequestBody CourseQuery courseQuery) {

        Page<EduCourse> page = eduCourseService.pageCondition(current, limit, courseQuery);

        return Result.ok().data("total", page.getTotal()).data("list", page.getRecords());
    }

    @ApiOperation("分页查询课程")
    @ApiImplicitParams({@ApiImplicitParam(name = "current", value = "当前页"), @ApiImplicitParam(name = "limit", value = "条数")})
    @PostMapping("/pageCourse/{current}/{limit}")
    public Result pageCourse(@PathVariable Integer current, @PathVariable Integer limit) {
        Page<EduCourse> page = new Page<>(current, limit);
        eduCourseService.page(page);
        return Result.ok().data("total", page.getTotal()).data("list", page.getRecords());
    }

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

    @ApiOperation("课程发布接口")
    @PostMapping("/publish/{courseId}")
    public Result publishCourse(@PathVariable String courseId) {
        eduCourseService.updateCourseStatus(courseId);
        return Result.ok();
    }

    @ApiOperation("获取课程确认界面Public")
    @GetMapping("/getPublicCourse/{courseId}")
    public Result getCoursePublic(@PathVariable String courseId) {
        CoursePublishVo coursePublishVo = eduCourseService.getPublic(courseId);
        return Result.ok().data("list", coursePublishVo);
    }


}
