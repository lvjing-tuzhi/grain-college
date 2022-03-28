package com.tuzhi.oss_service.controller;

import com.tuzhi.oss_service.service.OssService;
import com.tuzhi.utilcommon.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @program: guli_parent
 * @description: OSS上传文件
 * @author: 兔子
 * @create: 2022-03-28 22:19
 **/

@RestController
@RequestMapping("eduoss/fileoss")
@Api(tags = "文件上传")
@CrossOrigin
public class OssController {

    @Autowired
    OssService ossService;

    @PostMapping
    public Result uploadOssFile(MultipartFile file) {
        String fileName = ossService.uploadFile(file);
        return Result.ok().data("url", fileName);
    }

}
