package com.tuzhi.edu_service.service;

import com.tuzhi.edu_service.pojo.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tuzhi.edu_service.pojo.excel.OneSubject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author tuzhi
 * @since 2022-04-06
 */
public interface EduSubjectService extends IService<EduSubject> {

//    添加分类
    void saveSubject(EduSubjectService eduSubjectService, MultipartFile file);

//    获取树形分类数据
    List<OneSubject> getSubject();
}
