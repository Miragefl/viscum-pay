package com.viscum.pay.model.request.wxpay;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.viscum.pay.base.WxConstanst;
import com.viscum.pay.model.PayRequest;
import com.viscum.pay.model.response.wxpay.WxBalanceTransferQueryResponse;
import com.viscum.pay.util.Helper;
import com.viscum.pay.util.WxPayX509TrustManager;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WxBalanceTransferQueryRequest implements PayRequest<WxBalanceTransferQueryResponse> {
    @JsonProperty("nonce_str")
    private String nonceStr = Helper.getRandomChar(16);
    private String sign;
    @JsonProperty("partner_trade_no")
    private String partnerTradeNo;
    @JsonProperty("mch_id")
    private String mchId;
    @JsonProperty("appid")
    private String appId;


    @Override
    public Boolean needCert() {
        return true;
    }

    @Override
    public String getMethod() {
        return WxConstanst.BANK_TRANS_QUERY;
    }


    @Override
    public Class<WxBalanceTransferQueryResponse> getResponseClass() {
        return WxBalanceTransferQueryResponse.class;
    }

    @Override
    public String getVersion() {
        return null;
    }
}
