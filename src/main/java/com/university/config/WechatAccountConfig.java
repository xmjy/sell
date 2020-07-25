package com.university.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.net.ssl.SSLContext;
import java.util.Map;

/**
 * @author 林就远
 * @create 2019-03-08 11:12
 **/
@Data
@Component
@ConfigurationProperties(prefix = "wechat")
public class WechatAccountConfig {

    /* 公众号appid */
    private String mpAppId;

    /* 公众号appsecret */
    private String mpAppSecret;

    /* 商户号*/
    private String mchId;

    /* 商户秘钥 */
    private String mchKey;

    /* 商户证书路径 */
    private String keyPath;

    /* 微信支付异步通知地址*/
    private String notifyUrl;


    /* 微信开放平台id */
    private String openAppId;

    /*
    微信开放平台秘钥
     */
    private String openAppSecret;

    /*
    * 微信模板Id*/
    private Map<String,String> templateId;
}
