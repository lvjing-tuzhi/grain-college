package com.tuzhi.oss_service.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.tuzhi.oss_service.service.OssService;
import com.tuzhi.oss_service.util.ConstOssProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @program: guli_parent
 * @description: OSS业务实现类
 * @author: 兔子
 * @create: 2022-03-28 21:56
 **/

@Service
public class OssServiceImpl implements OssService {


    @Override
    public String uploadFile(MultipartFile file) {
        // 创建OSSClient实例。
        String endpoint = ConstOssProperties.ENDPOINT;
        String accessKeyId = ConstOssProperties.ACCESSKEYID;
        String accessKeyScret = ConstOssProperties.ACCESSKEYSCRET;
        String bucketName = ConstOssProperties.BUCKETNAME;
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeyScret);
        try {
            InputStream inputStream = file.getInputStream();
//            随机一个UUID作为文件名字
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
//            获得当前时间作为文件夹
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
            String dataString = simpleDateFormat.format(date);
            String fileName = "https://" + bucketName + "." + endpoint + "/" + dataString + "/" + uuid + file.getOriginalFilename();

            ossClient.putObject(bucketName, fileName, inputStream);
            return fileName;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }
}
