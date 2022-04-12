package com.tuzhi.edu_service.service;

import com.tuzhi.edu_service.pojo.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tuzhi.edu_service.pojo.chapter.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author tuzhi
 * @since 2022-04-09
 */
public interface EduChapterService extends IService<EduChapter> {

    //根据课程ID获取课程大纲
    List<ChapterVo> getChapter(String courseId);


    //删除章节
    void deleteChapter(String id);

    //根据课程id删除章节
    void removeByCourseId(String courseId);
}
