package com.tuzhi.service_msm.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.aliyun.teaopenapi.models.Config;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.google.gson.Gson;
import com.tuzhi.service_msm.service.MsmService;
import com.tuzhi.utilcommon.result.Result;
import com.tuzhi.utilcommon.result.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Map;

@Service
public class MsmServiceImpl implements MsmService {


    //发送短信的方法
    @Override
    public boolean send(Map<String, Object> param, String phone){
        //配置文件
        Config config = new Config()
                .setAccessKeyId("LTAI5t8UDYKyZiH7fLespJrm")
                .setAccessKeySecret("oeQPbwJ8aWSyG9Q2sKtDifmZN0iIOU");
        config.endpoint = "dysmsapi.aliyuncs.com";
        try {
            //客户端
            Client client = new Client(config);

            SendSmsRequest request = new SendSmsRequest();
            request.setPhoneNumbers(phone);
            request.setSignName("阿里云短信测试");
            request.setTemplateCode("SMS_154950909");
            request.setTemplateParam(JSONObject.toJSONString(param));

            //发送
            client.sendSms(request);

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
