package com.viscum.pay.model.request.wxpay.refund;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.viscum.pay.base.WxConstanst;
import com.viscum.pay.model.request.wxpay.WxRequest;
import com.viscum.pay.model.response.wxpay.refund.WxPayRefundQueryResponse;
import lombok.Data;

/**
 * 微信退款查询请求参数
 *
 * @author fenglei
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WxPayRefundQueryRequest implements WxRequest<WxPayRefundQueryResponse> {
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

    public WxPayRefundQueryRequest(String outRefundNo, String refundId) {
        this.outRefundNo = outRefundNo;
        this.refundId = refundId;
    }

    @Override
    @JsonIgnore
    public Boolean needCert() {
        return false;
    }

    @Override
    @JsonIgnore
    public String getMethod() {
        return WxConstanst.TRADE_REFUND_QUERY;
    }

    @Override
    @JsonIgnore
    public Class<WxPayRefundQueryResponse> getResponseClass() {
        return WxPayRefundQueryResponse.class;
    }
}
