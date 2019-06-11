package com.viscum.pay.config;

import lombok.Data;

/**
 * 微信请求配置
 *
 * @author fenglei
 */
@Data
public class WxPayConfig {

    /**
     * 公众号appId
     */
    private String appId;

    /**
     * 公众号appSecret
     */
    private String appSecret;

    /**
     * 商户号
     */
    private String mchId;

    /**
     * 商户密钥
     */
    private String mchKey;

    /**
     * 商户证书路径
     */
    private String keyPath;

    private String requestUrl;

    public WxPayConfig(String appId, String appSecret, String mchId, String mchKey, String keyPath, String requestUrl) {
        this.appId = appId;
        this.appSecret = appSecret;
        this.mchId = mchId;
        this.mchKey = mchKey;
        this.keyPath = keyPath;
        this.requestUrl = requestUrl;
    }
}
