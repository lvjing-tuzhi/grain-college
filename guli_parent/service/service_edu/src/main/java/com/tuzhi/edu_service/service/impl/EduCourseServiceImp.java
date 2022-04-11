package com.tuzhi.edu_service.service.impl;

import com.tuzhi.base_service.exceptionhandler.GuiException;
import com.tuzhi.edu_service.pojo.EduCourse;
import com.tuzhi.edu_service.mapper.EduCourseMapper;
import com.tuzhi.edu_service.pojo.EduCourseDescription;
import com.tuzhi.edu_service.pojo.vo.CourseInfoForm;
import com.tuzhi.edu_service.service.EduCourseDescriptionService;
import com.tuzhi.edu_service.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tuzhi.utilcommon.result.ResultCode;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author tuzhi
 * @since 2022-04-09
 */
@Service
public class EduCourseServiceImp extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    @Autowired
    EduCourseDescriptionService descriptionService;

//    添加课程和课程描述
    @Override
//    有报错就回滚
    @Transactional(rollbackFor = Exception.class)
    public String saveCourse(CourseInfoForm courseInfoForm) {
//        课程实体
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoForm, eduCourse);
//        添加课程数据库
        boolean save = this.save(eduCourse);
        if (!save) throw GuiException.from(ResultCode.SAVE_ERROR);
        String courseId = eduCourse.getId();

        //        课程描述实体
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        eduCourseDescription.setDescription(courseInfoForm.getDescription());
        eduCourseDescription.setId(courseId);
//        添加课程描述数据库
        boolean save1 = descriptionService.save(eduCourseDescription);
        if (!save1) throw GuiException.from(ResultCode.SAVE_ERROR);
        return courseId;
    }

    //    根据课程id获取前端课程信息列表
    @Override
    public CourseInfoForm getCourseById(String courseId) {
//        获取课程
        EduCourse course = this.getById(courseId);
//        获取课程描述
        EduCourseDescription description = descriptionService.getById(courseId);
//        封装courseInfoForm数据
        CourseInfoForm courseInfoForm = new CourseInfoForm();
        BeanUtils.copyProperties(course, courseInfoForm);
        courseInfoForm.setDescription(description.getDescription());
        return courseInfoForm;
    }

    // 修改课程和课程描述
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateCourse(CourseInfoForm courseInfoForm) {
        //修改课程
        EduCourse course = new EduCourse();
        BeanUtils.copyProperties(courseInfoForm, course);
        boolean b = this.updateById(course);
        if (!b) throw GuiException.from(ResultCode.UPDATE_ERROR);
//        修改课程描述
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        BeanUtils.copyProperties(courseInfoForm, eduCourseDescription);
        boolean b1 = descriptionService.updateById(eduCourseDescription);
        if (!b1) throw GuiException.from(ResultCode.UPDATE_ERROR);
    }
}
