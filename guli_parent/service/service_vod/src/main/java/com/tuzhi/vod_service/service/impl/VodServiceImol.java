package com.tuzhi.vod_service.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.req.UploadVideoRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyun.vod.upload.resp.UploadVideoResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.*;
import com.tuzhi.base_service.exceptionhandler.GuiException;
import com.tuzhi.utilcommon.result.ResultCode;
import com.tuzhi.vod_service.service.VodService;
import com.tuzhi.vod_service.util.ConstVodProperties;
import com.tuzhi.vod_service.util.InitVodClient;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @program: guli_parent
 * @description: 阿里云视频业务接口实现类
 * @author: 兔子
 * @create: 2022-04-14 20:58
 **/

@Service
public class VodServiceImol implements VodService {

    //上传视频
    @Override
    public String upload(MultipartFile file){
        //获取名字
        String originalFilename = file.getOriginalFilename();
        String title = originalFilename.substring(0, originalFilename.lastIndexOf("."));

        String videoId =null;

        //获取文件的输入流

        try {
            InputStream inputStream = file.getInputStream();
            UploadStreamRequest request = new UploadStreamRequest(ConstVodProperties.ACCESSKEYID, ConstVodProperties.ACCESSKEYSCRET, title, originalFilename, inputStream);
            UploadVideoImpl uploadVideo = new UploadVideoImpl();
            UploadStreamResponse response = uploadVideo.uploadStream(request);
            videoId = response.getVideoId();
            return videoId;
        } catch (IOException e) {
            e.printStackTrace();
            throw GuiException.from(ResultCode.UPLOAD_FILE_ERROR);
        }
    }

    //删除视频
    @Override
    public void delete(String videoId){
        try {
            DefaultAcsClient client = InitVodClient.initVodClient();
            DeleteVideoRequest request = new DeleteVideoRequest();
            //支持传入多个视频ID，多个用逗号分隔
            request.setVideoIds(videoId);
            client.getAcsResponse(request);
        } catch (ClientException e) {
            e.printStackTrace();
            throw GuiException.from(ResultCode.DELETE_ERROR);
        }
    }

    //获得视频播放地址
    @Override
    public String getPlayInfo(String videoId) {
        DefaultAcsClient client = InitVodClient.initVodClient();
        GetPlayInfoRequest request = new GetPlayInfoRequest();
        request.setVideoId(videoId);
        String playURL = null;
        try {
            GetPlayInfoResponse response = client.getAcsResponse(request);
            List<GetPlayInfoResponse.PlayInfo> playInfoList = response.getPlayInfoList();
            //播放地址
            for (GetPlayInfoResponse.PlayInfo playInfo : playInfoList) {
//                System.out.print("PlayInfo.PlayURL = " + playInfo.getPlayURL() + "\n");
                playURL = playInfo.getPlayURL();
            }
            return playURL;
        } catch (ClientException e) {
            e.printStackTrace();
            throw GuiException.from(ResultCode.ERROR);
        }
    }

    //获得视频播放凭证
    @Override
    public String getVideoPlayAuth(String vodeiId) {
        DefaultAcsClient client = InitVodClient.initVodClient();
        GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
        request.setVideoId(vodeiId);
        try {
            GetVideoPlayAuthResponse response = client.getAcsResponse(request);
            //播放凭证
//            System.out.print("PlayAuth = " + response.getPlayAuth() + "\n");
            return response.getPlayAuth();
        } catch (Exception e) {
            e.printStackTrace();
            throw GuiException.from(ResultCode.ERROR);
        }
    }

    //批量删除视频
    @Override
    public void deleteBatch(List<String> list) {
        try {
            DefaultAcsClient client = InitVodClient.initVodClient();
            DeleteVideoRequest request = new DeleteVideoRequest();
            String videoIds = StringUtils.join(list.toArray(), ",");
            //支持传入多个视频ID，多个用逗号分隔
            request.setVideoIds(videoIds);
            client.getAcsResponse(request);
        } catch (ClientException e) {
            e.printStackTrace();
            throw GuiException.from(ResultCode.DELETE_ERROR);
        }
    }


}
