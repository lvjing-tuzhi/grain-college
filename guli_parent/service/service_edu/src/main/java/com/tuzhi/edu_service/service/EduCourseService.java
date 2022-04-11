package com.tuzhi.edu_service.service;

import com.tuzhi.edu_service.pojo.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tuzhi.edu_service.pojo.vo.CourseInfoForm;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author tuzhi
 * @since 2022-04-09
 */
public interface EduCourseService extends IService<EduCourse> {

//    保存课程和课程描述
    String saveCourse(CourseInfoForm courseInfoForm);

//    根据课程id获取前端课程信息列表
    CourseInfoForm getCourseById(String courseId);

//    修改课程和课程描述
    void updateCourse(CourseInfoForm CourseInfoForm);
}
