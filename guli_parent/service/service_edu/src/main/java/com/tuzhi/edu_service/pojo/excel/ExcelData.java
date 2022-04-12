package com.tuzhi.edu_service.pojo.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: guli_parent
 * @description: 上传的Excel实体类
 * @author: 兔子
 * @create: 2022-04-06 20:00
 **/

@Data
@ApiModel
public class ExcelData {
    @ExcelProperty(index = 0)
    private String oneSubjectName;

    @ExcelProperty(index = 1)
    private String twoSubjectName;
}
