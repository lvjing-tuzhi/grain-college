package com.tuzhi.edu_service.service;

import com.tuzhi.edu_service.pojo.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author tuzhi
 * @since 2022-04-09
 */
public interface EduVideoService extends IService<EduVideo> {

    //根据课程id删除小节
    void removeByCourseId(String courseId);
}
