package com.viscum.pay.model.request.alipay;

import com.viscum.pay.base.Standard;
import com.viscum.pay.enums.SignType;
import com.viscum.pay.util.Helper;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 请求支付宝报文
 * @author fenglei
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AliCommonRequest {
    @JsonProperty("app_id")
    private String appId;
    private String format = Standard.FORMAT_JSON;
    private String charset = Standard.ENCODING_UTF8;
    private String method;
    @JsonProperty("sign_type")
    private SignType signType;
    private String timestamp = Helper.getCurrentTime("yyyy-MM-dd HH:mm:ss");
    private String version;
    @JsonProperty("notify_url")
    private String notifyUrl;
    @JsonProperty("return_url")
    private String returnUrl;
    @JsonProperty("biz_content")
    private String bizContent;
    private String sign;

    public AliCommonRequest(String appId, String method, SignType signType, String version, String notifyUrl, String returnUrl, String bizContent) {
        this.appId = appId;
        this.method = method;
        this.signType = signType;
        this.version = version;
        this.notifyUrl = notifyUrl;
        this.returnUrl = returnUrl;
        this.bizContent = bizContent;
    }
}
