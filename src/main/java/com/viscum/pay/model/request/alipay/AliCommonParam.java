package com.viscum.pay.model.request.alipay;

import com.viscum.pay.base.Standard;
import com.viscum.pay.util.Helper;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 请求支付宝报文
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AliCommonParam {
    @JsonProperty("app_id")
    private String appId;
    private String format;
    private String charset;
    private String method;
    @JsonProperty("sign_type")
    private String signType;
    private String timestamp;
    private String version;
    @JsonProperty("notify_url")
    private String notifyUrl;
    @JsonProperty("biz_content")
    private String bizContent;
    private String sign;

    public AliCommonParam(String appid, String notifyUrl, String method, String version, String bizContent) {
        this.appId = appid;
        this.notifyUrl = notifyUrl;
        this.format = Standard.FORMAT_JSON;
        this.charset = Standard.ENCODING_UTF8;
        this.signType = Standard.SIGN_TYPE_RSA2;
        this.method = method;
        this.timestamp = Helper.getCurrentTime("yyyy-MM-dd HH:mm:ss");
        this.version = version;
        this.bizContent = bizContent;
    }

}
