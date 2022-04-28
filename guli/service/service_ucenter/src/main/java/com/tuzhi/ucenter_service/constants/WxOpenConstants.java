package com.tuzhi.ucenter_service.constants;

import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
public class WxOpenConstants implements InitializingBean {

    /**
     * 微信开放平台appId
     */
    @Value("${wx.open.appId}")
    private String appId;

    /**
     * 微信开放平台appSecret
     */
    @Value("${wx.open.appSecret}")
    private String appSecret;

    /**
     * 微信开放平台 重定向url
     */
    @Value("${wx.open.redirectUrl}")
    private String redirectUrl;


    public static String WX_OPEN_APP_ID;
    public static String WX_OPEN_APP_SECRET;
    public static String WX_OPEN_REDIRECT_URL;

    @Override
    public void afterPropertiesSet() throws Exception {
        WX_OPEN_APP_ID = appId;
        WX_OPEN_APP_SECRET = appSecret;
        WX_OPEN_REDIRECT_URL = redirectUrl;
    }
}
