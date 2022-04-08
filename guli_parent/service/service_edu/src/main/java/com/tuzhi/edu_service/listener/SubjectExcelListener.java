package com.tuzhi.edu_service.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.CellData;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tuzhi.edu_service.pojo.EduSubject;
import com.tuzhi.edu_service.pojo.excel.ExcelData;
import com.tuzhi.edu_service.service.EduSubjectService;
import org.springframework.util.ObjectUtils;

import java.util.Map;

/**
 * @program: guli_parent
 * @description: 分类接口监听器
 * @author: 兔子
 * @create: 2022-04-06 20:09
 **/

public class SubjectExcelListener extends AnalysisEventListener<ExcelData> {

    private EduSubjectService eduSubjectService;

    public SubjectExcelListener(EduSubjectService eduSubjectService) {
        this.eduSubjectService = eduSubjectService;
    }

    @Override
    public void invoke(ExcelData excelData, AnalysisContext analysisContext) {
        if (ObjectUtils.isEmpty(excelData)){
            System.out.println("表格为空");
            throw new RuntimeException("分类表格不能为空");
        }
        EduSubject oneSuject = existOneSujectByName(excelData.getOneSubjectName());
//        没有一级分类则进行添加
        if (ObjectUtils.isEmpty(oneSuject)) {
            oneSuject = new EduSubject();
            oneSuject.setTitle(excelData.getOneSubjectName());
            oneSuject.setParentId("0");
            eduSubjectService.save(oneSuject);
        }
        String pid = oneSuject.getId();
        EduSubject twoSuject = existTwoSubjectByName(excelData.getTwoSubjectName(), pid);
        if (ObjectUtils.isEmpty(twoSuject)) {
            twoSuject = new EduSubject();
            twoSuject.setParentId(pid);
            twoSuject.setTitle(excelData.getTwoSubjectName());
            eduSubjectService.save(twoSuject);
        }

    }

    /**
     * 判断一级分类有没有存在
     * @param oneName
     * @return
     */
    private EduSubject existOneSujectByName(String oneName) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", oneName);
        wrapper.eq("parent_id", 0);
        EduSubject eduSubject = eduSubjectService.getOne(wrapper);
        return eduSubject;
    }

    /**
     * 判断二级分类有没有存在
     * @param twoName
     * @param pid
     * @return
     */
    private EduSubject existTwoSubjectByName(String twoName, String pid) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", twoName);
        wrapper.eq("parent_id", pid);
        EduSubject eduSubject = eduSubjectService.getOne(wrapper);
        return eduSubject;
    }


    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }

    @Override
    public void invokeHead(Map<Integer, CellData> headMap, AnalysisContext context) {
        System.out.println("表头：" + headMap);
    }
}
