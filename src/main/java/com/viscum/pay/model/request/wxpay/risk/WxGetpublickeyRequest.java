/*
 * WxGetpublickeyRequest.java Copyright BrightStars Tech Co. Ltd. All Rights Reserved.
 */
package com.viscum.pay.model.request.wxpay.risk;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.viscum.pay.base.WxConstanst;
import com.viscum.pay.model.request.wxpay.WxRequest;
import com.viscum.pay.model.response.wxpay.risk.WxGetpublickeyResponse;
import lombok.Data;

/**
 * <p>
 *
 * <p>
 *
 * @author fenglei
 * @since 2019-05-13
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WxGetpublickeyRequest implements WxRequest<WxGetpublickeyResponse> {
    /**
     * 微信支付分配的商户号
     */
    @JsonProperty("mch_id")
    private String mch_id;
    /**
     * 随机字符串，长度小于32位
     */
    @JsonProperty("nonce_str")
    private String nonce_str;
    /**
     * 商户自带签名
     */
    private String sign;
    /**
     * 签名类型，支持HMAC-SHA256和MD5。
     */
    @JsonProperty("sign_type")
    private String sign_type = "MD5";

    @Override
    public Boolean needCert() {
        return true;
    }

    @Override
    public String getMethod() {
        return WxConstanst.RISK_GET_PUBLIC_KEY;
    }

    @Override
    public Class<WxGetpublickeyResponse> getResponseClass() {
        return WxGetpublickeyResponse.class;
    }
}
