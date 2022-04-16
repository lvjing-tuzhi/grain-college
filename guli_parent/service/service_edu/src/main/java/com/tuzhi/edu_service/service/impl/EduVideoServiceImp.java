package com.tuzhi.edu_service.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tuzhi.edu_service.client.VodClient;
import com.tuzhi.edu_service.pojo.EduVideo;
import com.tuzhi.edu_service.mapper.EduVideoMapper;
import com.tuzhi.edu_service.service.EduVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    VodClient vodClient;


    //根据课程id删除小节:一个课程有多个小节，这个删除要删除多个小节
    @Override
    public void removeByCourseId(String courseId) {
        QueryWrapper<EduVideo> videoQueryWrapper = new QueryWrapper<>();
        videoQueryWrapper.eq("course_id", courseId);
        //删除多个小节视频
        List<EduVideo> list = this.list(videoQueryWrapper);
//        封装多个阿里云视频idList
        List<String> videoSourceList = list.stream()
                .filter(o -> !ObjectUtils.isEmpty(o.getVideoSourceId()))
                .map(o -> o.getVideoSourceId())
                .collect(Collectors.toList());
        vodClient.deleteVideoBatch(videoSourceList);

        //删除小节
        this.remove(videoQueryWrapper);
    }

    //根据小节id删除小节
    @Override
    public void removeVideoById(String id) {
        //删除阿里云视频
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id);
        wrapper.select("video_source_id");
        EduVideo eduVideo = this.getOne(wrapper);
        String videoSourceId = eduVideo.getVideoSourceId();
        if (!ObjectUtils.isEmpty(videoSourceId)) {
            vodClient.deleteVideo(eduVideo.getVideoSourceId());
        }

        //删除小节
        this.removeById(id);
    }
}
