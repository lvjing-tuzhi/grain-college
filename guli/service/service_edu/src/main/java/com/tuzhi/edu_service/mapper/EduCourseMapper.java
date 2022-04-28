package com.tuzhi.edu_service.mapper;

import com.tuzhi.edu_service.pojo.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tuzhi.edu_service.pojo.vo.CoursePublishVo;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author tuzhi
 * @since 2022-04-08
 */
@Mapper
public interface EduCourseMapper extends BaseMapper<EduCourse> {
    //获得课程确认信息
    CoursePublishVo getCoursePublicVo(String courseId);
}
