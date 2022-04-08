package com.tuzhi.edu_service.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tuzhi.edu_service.listener.SubjectExcelListener;
import com.tuzhi.edu_service.pojo.EduSubject;
import com.tuzhi.edu_service.mapper.EduSubjectMapper;
import com.tuzhi.edu_service.pojo.excel.ExcelData;
import com.tuzhi.edu_service.pojo.excel.OneSubject;
import com.tuzhi.edu_service.pojo.excel.TwoSubject;
import com.tuzhi.edu_service.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    //    添加分类
    @Override
    public void saveSubject(EduSubjectService eduSubjectService, MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();
            EasyExcel.read(inputStream, ExcelData.class, new SubjectExcelListener(eduSubjectService)).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //    获取树形分类数据
//    @Override
//    public List<OneSubject> getSubject() {
////        获取一级分类
//        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
//        wrapper.eq("parent_id", "0");
////        使用父类的crud方法
//        List<EduSubject> oneSuject = baseMapper.selectList(wrapper);
//        System.out.println("=====>oneSubject" + oneSuject);
//
////        获取二级分类
//        QueryWrapper<EduSubject> wrapper1 = new QueryWrapper<>();
//        wrapper1.ne("parent_id", "0");
//        List<EduSubject> twoSubject = baseMapper.selectList(wrapper1);
//        System.out.println("======>" + twoSubject);
//
////        要返回的数据
//        ArrayList<OneSubject> list = new ArrayList<>();
////        封装一级分类
//        for (EduSubject eduSubject : oneSuject) {
//            OneSubject tempOne = new OneSubject();
//            BeanUtils.copyProperties(eduSubject, tempOne);
//
////            封装二级分类
//            ArrayList<TwoSubject> twoList = new ArrayList<>();
//            for (EduSubject subject : twoSubject) {
//                TwoSubject tempTwo = new TwoSubject();
//
//                System.out.println("判断" + eduSubject.getId() + " ==>  " + subject.getParentId());
//                if (eduSubject.getId().equals(subject.getParentId())) {
//                    System.out.println("进行添加childed");
//                    BeanUtils.copyProperties(subject, tempTwo);
//                    twoList.add(tempTwo);
//                }
//            }
//            tempOne.setChildren(twoList);
//            list.add(tempOne);
//        }
//
//        return list;
//    }

    //    获取树形分类数据,使用stream流方式
    @Override
    public List<OneSubject> getSubject() {
        List<EduSubject> subject = this.list();

//        封装一级分类
        List<OneSubject> oneSubjectsList = subject.stream()
        .filter(o -> o.getParentId().equals("0")) //过滤if判断放回符合条件的
        .map(o -> { //返回指定类型
            OneSubject oneSubject = new OneSubject();
            BeanUtils.copyProperties(o, oneSubject);
            return oneSubject;
        })
        .collect(Collectors.toList());
//        封装二级分类
        oneSubjectsList.forEach(o -> {
            List<TwoSubject> twoSubjectList = subject.stream()
            .filter(o1 -> o1.getParentId().equals(o.getId()))
            .map(o2 -> {
                TwoSubject twoSubject = new TwoSubject();
                BeanUtils.copyProperties(o2, twoSubject);
                return twoSubject;
            })
            .collect(Collectors.toList());
            o.setChildren(twoSubjectList);
        });
        return oneSubjectsList;
    }
}
