/*
 * WxGetpublickeyRequest.java Copyright BrightStars Tech Co. Ltd. All Rights Reserved.
 */
package com.viscum.pay.model.request.wxpay.risk;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private String mchId;
    /**
     * 随机字符串，长度小于32位
     */
    @JsonProperty("nonce_str")
    private String nonceStr;
    /**
     * 商户自带签名
     */
    private String sign;
    /**
     * 签名类型，支持HMAC-SHA256和MD5。
     */
    @JsonProperty("sign_type")
    private String signType = "MD5";

    @Override
    @JsonIgnore
    public Boolean needCert() {
        return true;
    }

    @Override
    @JsonIgnore
    public String getMethod() {
        return WxConstanst.RISK_GET_PUBLIC_KEY;
    }

    @Override
    @JsonIgnore
    public Class<WxGetpublickeyResponse> getResponseClass() {
        return WxGetpublickeyResponse.class;
    }
}
