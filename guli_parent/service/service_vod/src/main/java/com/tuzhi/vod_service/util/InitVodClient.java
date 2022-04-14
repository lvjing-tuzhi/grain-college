package com.tuzhi.vod_service.util;

import com.aliyun.oss.ClientException;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.profile.DefaultProfile;

/**
 * @program: guli_parent
 * @description:
 * @author: 兔子
 * @create: 2022-04-14 20:08
 **/

public class InitVodClient {
    public static DefaultAcsClient initVodClient() throws ClientException {
        String regionId = "cn-shanghai";  // 点播服务接入地域
        DefaultProfile profile = DefaultProfile.getProfile(regionId, ConstVodProperties.ACCESSKEYID, ConstVodProperties.ACCESSKEYSCRET);
        DefaultAcsClient client = new DefaultAcsClient(profile);
        return client;
    }
}
