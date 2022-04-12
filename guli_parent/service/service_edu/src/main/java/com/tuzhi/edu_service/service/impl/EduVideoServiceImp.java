package com.tuzhi.edu_service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tuzhi.edu_service.pojo.EduVideo;
import com.tuzhi.edu_service.mapper.EduVideoMapper;
import com.tuzhi.edu_service.service.EduVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author tuzhi
 * @since 2022-04-09
 */
@Service
public class EduVideoServiceImp extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {

    //根据课程id删除小节
    @Override
    public void removeByCourseId(String courseId) {
        QueryWrapper<EduVideo> videoQueryWrapper = new QueryWrapper<>();
        videoQueryWrapper.eq("course_id", courseId);
        this.remove(videoQueryWrapper);
    }
}
