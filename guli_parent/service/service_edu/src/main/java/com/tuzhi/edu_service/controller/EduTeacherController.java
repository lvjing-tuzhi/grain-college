package com.tuzhi.edu_service.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tuzhi.edu_service.pojo.EduTeacher;
import com.tuzhi.edu_service.pojo.vo.TeacherQuery;
import com.tuzhi.edu_service.service.IEduTeacherService;
import com.tuzhi.utilcommon.result.Result;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author tuzhi
 * @since 2022-03-06
 */
@RestController
@RequestMapping("/eduservice/teacher")
@Api(tags = "教师接口")
@CrossOrigin
public class EduTeacherController {

    @Autowired
    IEduTeacherService teacherService;

    @GetMapping("/findAll")
    @ApiOperation("查询所有教师列表")
    public Result findAll() {
        List<EduTeacher> list = teacherService.list();
        return Result.ok().data("datas", list);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("逻辑删除教师")
    public Result removeTeacher(@ApiParam(name = "id", value = "教师id") @PathVariable String id) {
        boolean b = teacherService.removeById(id);
        if (b) {
            return Result.ok();
        }else {
            return Result.error();
        }
    }

    @ApiOperation("分页查询教师")
    @ApiImplicitParams({@ApiImplicitParam(name = "current", value = "当前页"), @ApiImplicitParam(name = "limit", value = "条数")})
    @GetMapping("/pageTeacher/{current}/{limit}")
    public Result pageTeacher(@PathVariable Integer current, @PathVariable Integer limit) {
        Page<EduTeacher> page = new Page<>(current, limit);
        teacherService.page(page);
        return Result.ok().data("total", page.getTotal()).data("rows", page.getRecords());
    }

    @ApiOperation("多条件分页查询")
    @PostMapping("/pageTeacherCondition/{current}/{limit}")
    @ApiImplicitParams({@ApiImplicitParam(name = "current", value = "当前页"), @ApiImplicitParam(name = "limit", value = "条数")})
    public Result pageTeacherConditoin(@PathVariable Integer current, @PathVariable Integer limit
            , @RequestBody TeacherQuery teacherQuery) {

        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();
        System.out.println(teacherQuery);
        if (StringUtils.hasLength(name)) {
            wrapper.like("name", name);
            System.out.println("进来了");
        }
        if (!ObjectUtils.isEmpty(level)) {
            wrapper.eq("level", level);
        }
        if (StringUtils.hasLength(begin)) {
            wrapper.ge("gmt_create", begin);
        }
        if (StringUtils.hasLength(end)) {
            wrapper.ge("gmt_modified", end);
        }
        Page<EduTeacher> page = new Page<>(current, limit);
        teacherService.page(page, wrapper);
        return Result.ok().data("total", page.getTotal()).data("rows", page.getRecords());
    }

    @ApiOperation("添加教师")
    @PostMapping("/addTeacher")
    public Result addTeacher(@RequestBody EduTeacher teacher) {
        boolean save = teacherService.save(teacher);
        if (save) {
            return Result.ok();
        }else {
            return Result.error();
        }
    }

    @ApiOperation("修改教师")
    @PostMapping("/updateTeacher")
    public Result updateTeacher(@RequestBody EduTeacher teacher) {
        boolean update = teacherService.updateById(teacher);
        if (update) {
            return Result.ok();
        }else {
            return Result.error();
        }
    }
}
