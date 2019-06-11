package com.viscum.pay.config;

import com.viscum.pay.enums.SignType;
import lombok.Data;

/**
 * @author fenglei
 */
@Data
public class AliPayConfig {

    /**
     * 应用id
     */
    private String appId;

    /**
     * 支付宝公钥
     */
    private String aliPublicKey;

    /**
     * 应用私钥.
     */
    private String appPrivateKey;

    /**
     * 应用公钥.
     */
    private String appPublicKey;

    /**
     * 签名方式: RSA, RSA2两个值可选, 必须大写.
     */
    private SignType signType;

    private String notifyUrl;

    private String returnUrl;

    private String requestUrl;


    public AliPayConfig(String appId, String aliPublicKey, String appPrivateKey, String appPublicKey, SignType signType, String notifyUrl, String returnUrl, String requestUrl) {
        this.appId = appId;
        this.aliPublicKey = aliPublicKey;
        this.appPrivateKey = appPrivateKey;
        this.appPublicKey = appPublicKey;
        this.signType = signType;
        this.notifyUrl = notifyUrl;
        this.returnUrl = returnUrl;
        this.requestUrl = requestUrl;
    }
}
