package com.youke.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
public class BaseConfiguration {

    //腾讯云的SecretId
    @Value("${cos.tencent.SecretId}")
    private String secretId;

    //腾讯云的SecretKey
    @Value("${cos.tencent.SecretKey}")
    private String secretKey;

    //腾讯云的bucket (存储桶)
    @Value("${cos.tencent.bucket}")
    private String bucket;

    //腾讯云的region(bucket所在地区)
    @Value("${cos.tencent.region}")
    private String region;

    //腾讯云的allowPrefix(允许上传的路径)
    @Value("${cos.tencent.allowPrefix}")
    private String allowPrefix;

    //腾讯云的临时密钥时长(单位秒)
    @Value("${cos.tencent.durationSeconds}")
    private String durationSeconds;

    //腾讯云的访问基础链接:
    @Value("${cos.tencent.baseUrl}")
    private String baseUrl;

    //微信小程序appid
    @Value("${wx.appid}")
    private String appid;

    //微信小程序secret
    @Value("${wx.secret}")
    private String secret;

    //微信小程序测试appid
    @Value("${wx.dev.appid}")
    private String devAppid;

    //微信小程序测试secret
    @Value("${wx.dev.secret}")
    private String devSecret;
}
