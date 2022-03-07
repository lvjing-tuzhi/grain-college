package com.tuzhi.edu_service.controller;

import com.tuzhi.edu_service.pojo.EduTeacher;
import com.tuzhi.edu_service.service.IEduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/edu_service/teacher")
@Api("教师控制层")
public class EduTeacherController {

    @Autowired
    IEduTeacherService teacherService;

    @GetMapping("/findAll")
    @ApiOperation("查询所有教师列表")
    public List<EduTeacher> findAll() {
        List<EduTeacher> list = teacherService.list();
        return list;
    }

    @DeleteMapping("/{id}")
    @ApiOperation("逻辑删除教师")
    public boolean removeTeacher(@PathVariable String id) {
        boolean b = teacherService.removeById(id);
        return b;
    }
}
