package com.tuzhi.edu_service.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tuzhi.edu_service.pojo.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tuzhi.edu_service.pojo.vo.CourseInfoForm;
import com.tuzhi.edu_service.pojo.vo.CoursePublishVo;
import com.tuzhi.edu_service.pojo.vo.CourseQuery;
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

    //    课程发布接口,修改课程状态
    void updateCourseStatus(String courseId);

    //课程确认
    CoursePublishVo getPublic(String courseId);

    //    多条件分页查询
    Page<EduCourse> pageCondition(Integer current, Integer limit, CourseQuery courseQuery);

    //删除课程、章节、小节、课程描述
    void removeCourseById(String courseId);
}
