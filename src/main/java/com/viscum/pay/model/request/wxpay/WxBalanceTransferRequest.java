package com.viscum.pay.model.request.wxpay;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.viscum.pay.base.WxConstanst;
import com.viscum.pay.model.PayRequest;
import com.viscum.pay.model.response.wxpay.WxBalanceTransferResponse;
import com.viscum.pay.model.response.wxpay.WxBankTransferResponse;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WxBalanceTransferRequest implements PayRequest<WxBalanceTransferResponse> {
    //    @JsonProperty("mch_appid")
//    private String appId;
//    @JsonProperty("mchid")
//    private String mchId;
    @JsonProperty("device_info")
    private String deviceInfo;
    //    @JsonProperty("nonce_str")
//    private String nonceStr;
//    private String sign;
    @JsonProperty("partner_trade_no")
    private String partnerTradeNo;
    @JsonProperty("openid")
    private String openId;
    /**
     * NO_CHECK：不校验真实姓名
     * FORCE_CHECK：强校验真实姓名
     */
    @JsonProperty("check_name")
    private String checkName;
    @JsonProperty("re_user_name")
    private String reUserName;
    private String amount;
    private String desc;
    @JsonProperty("spbill_create_ip")
    private String spbillCreateIp;


    @Override
    public Boolean needCert() {
        return true;
    }

    @Override
    public String getMethod() {
        return WxConstanst.BALANCE_TRANSFER;
    }

    @Override
    public String getServerUrl() {
        return WxConstanst.SERVER_URL;
    }

    @Override
    public Class<WxBalanceTransferResponse> getResponseClass() {
        return WxBalanceTransferResponse.class;
    }
}
