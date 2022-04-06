package com.tuzhi.edu_service.service;

import com.tuzhi.edu_service.pojo.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author tuzhi
 * @since 2022-04-06
 */
public interface EduSubjectService extends IService<EduSubject> {

    void saveSubject(EduSubjectService eduSubjectService, MultipartFile file);
}
