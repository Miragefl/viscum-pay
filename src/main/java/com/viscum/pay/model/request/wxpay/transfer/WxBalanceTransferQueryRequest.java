package com.viscum.pay.model.request.wxpay.transfer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.viscum.pay.base.WxConstanst;
import com.viscum.pay.model.request.wxpay.WxRequest;
import com.viscum.pay.model.response.wxpay.transfer.WxBalanceTransferQueryResponse;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WxBalanceTransferQueryRequest implements WxRequest<WxBalanceTransferQueryResponse> {
    @JsonProperty("nonce_str")
    private String nonceStr;
    private String sign;
    @JsonProperty("partner_trade_no")
    private String partnerTradeNo;
    @JsonProperty("mch_id")
    private String mchId;
    @JsonProperty("appid")
    private String appId;

    public WxBalanceTransferQueryRequest(String partnerTradeNo) {
        this.partnerTradeNo = partnerTradeNo;
    }

    @Override
    public Boolean needCert() {
        return true;
    }

    @Override
    public String getMethod() {
        return WxConstanst.BALANCE_TRANS_QUERY;
    }

    @Override
    public Class<WxBalanceTransferQueryResponse> getResponseClass() {
        return WxBalanceTransferQueryResponse.class;
    }
}
