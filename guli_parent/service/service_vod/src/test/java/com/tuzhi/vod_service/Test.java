package com.tuzhi.vod_service;

import com.aliyun.vod.upload.impl.UploadImageImpl;
import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.*;
import com.aliyun.vod.upload.resp.*;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.*;
import com.tuzhi.vod_service.util.ConstVodProperties;
import com.tuzhi.vod_service.util.InitVodClient;

import java.io.*;
import java.net.URL;
import java.util.List;

/**
 * @program: guli_parent
 * @description:
 * @author: 兔子
 * @create: 2022-04-14 16:32
 **/

public class Test {

    public static void main(String[] args) {
        // 一、视频文件上传
        // 视频标题(必选)
//        String title = "测试标题";
//        String fileName = "C:\\Users\\Lenovo\\Desktop\\谷粒学院学习\\guli_parent-master\\Doc\\1-阿里云上传测试视频\\6 - What If I Want to Move Faster.mp4";
//        System.out.println(accessKeyId + "     " + accessKeySecret);
//        // 本地文件上传
//        testUploadVideo(accessKeyId, accessKeySecret, title, fileName);

        //二、获取视频播放地址
//        DefaultAcsClient client = InitVodClient.initVodClient();
//        GetPlayInfoResponse response = new GetPlayInfoResponse();
//        try {
//            response = getPlayInfo(client);
//            List<GetPlayInfoResponse.PlayInfo> playInfoList = response.getPlayInfoList();
//            //播放地址
//            for (GetPlayInfoResponse.PlayInfo playInfo : playInfoList) {
//                System.out.print("PlayInfo.PlayURL = " + playInfo.getPlayURL() + "\n");
//            }
//            //Base信息
//            System.out.print("VideoBase.Title = " + response.getVideoBase().getTitle() + "\n");
//        } catch (Exception e) {
//            System.out.print("ErrorMessage = " + e.getLocalizedMessage());
//        }
//        System.out.print("RequestId = " + response.getRequestId() + "\n");
        //获取视频播放凭证
//        DefaultAcsClient client = InitVodClient.initVodClient();
//        GetVideoPlayAuthResponse response = new GetVideoPlayAuthResponse();
//        try {
//            response = getVideoPlayAuth(client);
//            //播放凭证
//            System.out.print("PlayAuth = " + response.getPlayAuth() + "\n");
//            //VideoMeta信息
//            System.out.print("VideoMeta.Title = " + response.getVideoMeta().getTitle() + "\n");
//        } catch (Exception e) {
//            System.out.print("ErrorMessage = " + e.getLocalizedMessage());
//        }
//        System.out.print("RequestId = " + response.getRequestId() + "\n");
    }




    /*获取播放凭证函数*/
    public static GetVideoPlayAuthResponse getVideoPlayAuth(DefaultAcsClient client) throws Exception {
        GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
        request.setVideoId("30cd392192974cfc91cba5587b20f8cf");
        return client.getAcsResponse(request);
    }

    /*获取播放地址函数*/
    public static GetPlayInfoResponse getPlayInfo(DefaultAcsClient client) throws Exception {
        GetPlayInfoRequest request = new GetPlayInfoRequest();
        request.setVideoId("30cd392192974cfc91cba5587b20f8cf");
        return client.getAcsResponse(request);
    }

    //删除视频
    public static DeleteVideoResponse deleteVideo(DefaultAcsClient client) throws Exception {
        DeleteVideoRequest request = new DeleteVideoRequest();
        //支持传入多个视频ID，多个用逗号分隔
        request.setVideoIds("VideoId1,VideoId2");
        return client.getAcsResponse(request);
    }

    /**
     * 本地文件上传接口
     *
     * @param accessKeyId
     * @param accessKeySecret
     * @param title
     * @param fileName
     */
    private static void testUploadVideo(String accessKeyId, String accessKeySecret, String title, String fileName) {
        UploadVideoRequest request = new UploadVideoRequest(accessKeyId, accessKeySecret, title, fileName);
        /* 可指定分片上传时每个分片的大小，默认为2M字节 */
        request.setPartSize(2 * 1024 * 1024L);
        /* 可指定分片上传时的并发线程数，默认为1，(注：该配置会占用服务器CPU资源，需根据服务器情况指定）*/
        request.setTaskNum(1);

        UploadVideoImpl uploader = new UploadVideoImpl();
        UploadVideoResponse response = uploader.uploadVideo(request);
        System.out.print("RequestId=" + response.getRequestId() + "\n");  //请求视频点播服务的请求ID
        if (response.isSuccess()) {
            System.out.print("VideoId=" + response.getVideoId() + "\n");
        } else {
            /* 如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因 */
            System.out.print("VideoId=" + response.getVideoId() + "\n");
            System.out.print("ErrorCode=" + response.getCode() + "\n");
            System.out.print("ErrorMessage=" + response.getMessage() + "\n");
        }
    }


}
