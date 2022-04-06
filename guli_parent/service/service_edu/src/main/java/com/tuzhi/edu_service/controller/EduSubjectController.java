package com.tuzhi.edu_service.controller;

import com.tuzhi.edu_service.service.EduSubjectService;
import com.tuzhi.utilcommon.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author tuzhi
 * @since 2022-04-06
 */
@RestController
@RequestMapping("/eduservice/eduSubject")
@CrossOrigin
@Api(tags = "课程分类接口")
public class EduSubjectController {

    @Autowired
    EduSubjectService eduSubjectService;

    public EduSubjectController(EduSubjectService eduSubjectService) {
        this.eduSubjectService = eduSubjectService;
    }

    @PostMapping("/addSubject")
    @ApiOperation("上传Excel添加分类接口")
    public Result addSubject(MultipartFile file) {
        eduSubjectService.saveSubject(eduSubjectService, file);
        return Result.ok();
    }
}
