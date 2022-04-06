package com.tuzhi.edu_service.service.impl;

import com.alibaba.excel.EasyExcel;
import com.tuzhi.edu_service.listener.SubjectExcelListener;
import com.tuzhi.edu_service.pojo.EduSubject;
import com.tuzhi.edu_service.mapper.EduSubjectMapper;
import com.tuzhi.edu_service.pojo.excel.SubjectData;
import com.tuzhi.edu_service.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author tuzhi
 * @since 2022-04-06
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    @Override
    public void saveSubject(EduSubjectService eduSubjectService, MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();
            EasyExcel.read(inputStream, SubjectData.class, new SubjectExcelListener(eduSubjectService)).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
