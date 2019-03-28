package com.viscum.pay.model.request.wxpay;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.viscum.pay.base.WxConstanst;
import com.viscum.pay.model.PayRequest;
import com.viscum.pay.model.response.wxpay.WxBankTransferQueryResponse;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WxBankTransferQueryRequest implements PayRequest<WxBankTransferQueryResponse> {
    //    @JsonProperty("mchid")
//    private String mchId;
    @JsonProperty("partner_trade_no")
    private String partnerTradeNo;
//    @JsonProperty("nonce_str")
//    private String nonceStr;
//    private String sign;


    @Override
    public Boolean needCert() {
        return true;
    }

    @Override
    public String getMethod() {
        return WxConstanst.BANK_TRANS_QUERY;
    }

    @Override
    public String getServerUrl() {
        return WxConstanst.SERVER_URL;
    }

    @Override
    public Class<WxBankTransferQueryResponse> getResponseClass() {
        return WxBankTransferQueryResponse.class;
    }
}
