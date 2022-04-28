package com.tuzhi.oss_service.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @program: guli_parent
 * @description: OSS业务接口
 * @author: 兔子
 * @create: 2022-03-28 21:55
 **/

public interface OssService {
//    上传文件接口
    String uploadFile(MultipartFile file);
}
