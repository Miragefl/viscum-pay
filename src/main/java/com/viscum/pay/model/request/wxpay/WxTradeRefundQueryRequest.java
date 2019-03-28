package com.viscum.pay.model.request.wxpay;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.viscum.pay.base.WxConstanst;
import com.viscum.pay.model.PayRequest;
import com.viscum.pay.model.response.wxpay.WxTradeRefundQueryResponse;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WxTradeRefundQueryRequest implements PayRequest<WxTradeRefundQueryResponse> {
    @JsonProperty("appid")
    private String appId;
    @JsonProperty("mch_id")
    private String mchId;
    @JsonProperty("nonce_str")
    private String nonceStr;
    private String sign;
    @JsonProperty("transaction_id")
    private String transactionId;
    @JsonProperty("out_trade_no")
    private String outTradeNo;
    @JsonProperty("out_refund_no")
    private String outRefundNo;
    @JsonProperty("refund_id")
    private String refundId;

    public WxTradeRefundQueryRequest(String outRefundNo, String refundId) {
        this.outRefundNo = outRefundNo;
        this.refundId = refundId;
    }

    @Override
    public Boolean needCert() {
        return false;
    }

    @Override
    public String getMethod() {
        return WxConstanst.TRADE_REFUND_QUERY;
    }

    @Override
    public String getServerUrl() {
        return WxConstanst.SERVER_URL;
    }

    @Override
    public Class<WxTradeRefundQueryResponse> getResponseClass() {
        return WxTradeRefundQueryResponse.class;
    }
}
