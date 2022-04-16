package com.tuzhi.vod_service.service;

import com.aliyuncs.exceptions.ClientException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @program: guli_parent
 * @description:阿里云视频业务接口
 * @author: 兔子
 * @create: 2022-04-14 20:57
 **/

public interface VodService {
    //上传视频
    String upload(MultipartFile file);

    //删除视频
    void delete(String videoId);

    //获取视频地址
    String getPlayInfo(String videoId);

    //获取视频凭证
    String getVideoPlayAuth(String vodeiId);

    //批量删除视频
    void deleteBatch(List<String> list);
}
