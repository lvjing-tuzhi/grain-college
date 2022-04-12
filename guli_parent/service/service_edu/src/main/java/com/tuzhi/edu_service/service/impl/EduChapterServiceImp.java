package com.tuzhi.edu_service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tuzhi.base_service.exceptionhandler.GuiException;
import com.tuzhi.edu_service.pojo.EduChapter;
import com.tuzhi.edu_service.mapper.EduChapterMapper;
import com.tuzhi.edu_service.pojo.EduVideo;
import com.tuzhi.edu_service.pojo.chapter.ChapterVo;
import com.tuzhi.edu_service.pojo.chapter.VideoVo;
import com.tuzhi.edu_service.service.EduChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tuzhi.edu_service.service.EduVideoService;
import com.tuzhi.utilcommon.result.ResultCode;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author tuzhi
 * @since 2022-04-09
 */
@Service
public class EduChapterServiceImp extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {

    @Autowired
    EduVideoService videoService;

    //根据课程ID获取课程大纲
    @Override
    public List<ChapterVo> getChapter(String courseId) {
        //获取章节
        QueryWrapper<EduChapter> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id", courseId);
        List<EduChapter> chapters = this.list(wrapper);

        //获取小结
        QueryWrapper<EduVideo> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("course_id", courseId);
        List<EduVideo> eduVideos = videoService.list(wrapper1);

        //封装课程大纲
        List<ChapterVo> chapterTreeList = chapters.stream()
                //返回课程大纲数据
                .map(o -> {
                    //封装一级
                    ChapterVo chapterVo = new ChapterVo();
                    BeanUtils.copyProperties(o, chapterVo);

//                    封装二级
                    List<VideoVo> videoVoList = eduVideos.stream()
                            .filter(videoFO -> {
                                return chapterVo.getId().equals(videoFO.getChapterId());
                            })
                            .map(videoMO -> {
                                VideoVo videoVo = new VideoVo();
                                BeanUtils.copyProperties(videoMO, videoVo);
                                return videoVo;
                            })
                            .collect(Collectors.toList());
                    //把二级添加到一级中
                    chapterVo.setChildren(videoVoList);
                    return chapterVo;
                })
                .collect(Collectors.toList());
        return chapterTreeList;
    }

    @Override
    public void deleteChapter(String id) {
        //根据chapterid章节id 查询小节表，如果查询数据，不进行删除
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("chapter_id",id);
        long count = videoService.count(wrapper);
        //判断
        if(count >0) {//查询出小节，不进行删除
            throw GuiException.from(ResultCode.DELETE_ERROR);
        }
        //删除章节
        int result = baseMapper.deleteById(id);
        }

    @Override
    public void removeByCourseId(String courseId) {
        QueryWrapper<EduChapter> chapterQueryWrapper = new QueryWrapper<>();
        chapterQueryWrapper.eq("course_id", courseId);
        this.remove(chapterQueryWrapper);
    }

}
