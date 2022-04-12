package com.tuzhi.edu_service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tuzhi.base_service.exceptionhandler.GuiException;
import com.tuzhi.edu_service.pojo.EduChapter;
import com.tuzhi.edu_service.pojo.EduCourse;
import com.tuzhi.edu_service.mapper.EduCourseMapper;
import com.tuzhi.edu_service.pojo.EduCourseDescription;
import com.tuzhi.edu_service.pojo.EduVideo;
import com.tuzhi.edu_service.pojo.vo.CourseInfoForm;
import com.tuzhi.edu_service.pojo.vo.CoursePublishVo;
import com.tuzhi.edu_service.pojo.vo.CourseQuery;
import com.tuzhi.edu_service.service.EduChapterService;
import com.tuzhi.edu_service.service.EduCourseDescriptionService;
import com.tuzhi.edu_service.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tuzhi.edu_service.service.EduVideoService;
import com.tuzhi.utilcommon.result.ResultCode;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

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

    //课程描述
    @Autowired
    EduCourseDescriptionService descriptionService;

    //课程章节
    @Autowired
    EduChapterService chapterService;

    //课程小节
    @Autowired
    EduVideoService videoService;


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

    //课程发布接口,修改课程状态
    @Override
    public void updateCourseStatus(String courseId) {
        EduCourse eduCourse = new EduCourse();
        eduCourse.setId(courseId);
        eduCourse.setStatus("Normal");
        this.updateById(eduCourse);
    }

    //课程确认
    @Override
    public CoursePublishVo getPublic(String courseId) {
        CoursePublishVo coursePublic = baseMapper.getCoursePublicVo(courseId);
        return coursePublic;
    }

//    多条件分页查询
    @Override
    public Page<EduCourse> pageCondition(Integer current, Integer limit, CourseQuery courseQuery) {
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        String title = courseQuery.getTitle();
        String teacherId = courseQuery.getTeacherId();
        String subjectId = courseQuery.getSubjectId();
        String subjectParentId = courseQuery.getSubjectParentId();
        String buyCountSort = courseQuery.getBuyCountSort();
        String gmtCreateSort = courseQuery.getGmtCreateSort();
        String priceSort = courseQuery.getPriceSort();

        if (!ObjectUtils.isEmpty(title))  wrapper.like("title", title);
        if (!ObjectUtils.isEmpty(teacherId)) wrapper.eq("teacherId", teacherId);
        if (!ObjectUtils.isEmpty(subjectId)) wrapper.eq("subjectId", subjectId);
        if (!ObjectUtils.isEmpty(subjectParentId)) wrapper.eq("subjectParentId", subjectParentId);
        if (!ObjectUtils.isEmpty(buyCountSort)) wrapper.orderByDesc("buyCount");
        if (!ObjectUtils.isEmpty(gmtCreateSort)) wrapper.orderByDesc("gmtCreate");
        if (!ObjectUtils.isEmpty(priceSort)) wrapper.orderByDesc("price");

        Page<EduCourse> page = new Page<>(current, limit);
        this.page(page, wrapper);
        return page;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeCourseById(String courseId) {
        //删除课程章节
        chapterService.removeByCourseId(courseId);

        //删除课程小节
        videoService.removeByCourseId(courseId);

        //删除课程描述
        boolean remove2 = descriptionService.removeById(courseId);
        if (!remove2) throw GuiException.from(ResultCode.DELETE_ERROR);
        //删除课程
        boolean b = this.removeById(courseId);
        if (!b) throw GuiException.from(ResultCode.DELETE_ERROR);
    }
}
