package com.viscum.pay.model.request.wxpay.transfer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.viscum.pay.base.WxConstanst;
import com.viscum.pay.model.request.wxpay.WxRequest;
import com.viscum.pay.model.response.wxpay.transfer.WxBankTransferQueryResponse;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WxBankTransferQueryRequest implements WxRequest<WxBankTransferQueryResponse> {
    @JsonProperty("mchid")
    private String mchId;
    @JsonProperty("partner_trade_no")
    private String partnerTradeNo;
    @JsonProperty("nonce_str")
    private String nonceStr;
    private String sign;


    @Override
    @JsonIgnore
    public Boolean needCert() {
        return true;
    }

    @Override
    @JsonIgnore
    public String getMethod() {
        return WxConstanst.BANK_TRANS_QUERY;
    }

    @Override
    @JsonIgnore
    public Class<WxBankTransferQueryResponse> getResponseClass() {
        return WxBankTransferQueryResponse.class;
    }
}
