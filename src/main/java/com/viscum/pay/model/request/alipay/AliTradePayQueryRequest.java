package com.viscum.pay.model.request.alipay;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 支付订单查询请求参数
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AliTradePayQueryRequest {
    /**
     * 订单支付时传入的商户订单号,和支付宝交易号不能同时为空。
     * trade_no,out_trade_no如果同时存在优先取trade_no
     */
    @JsonProperty("out_trade_no")
    private String outTradeNo;
    /**
     * 支付宝交易号，和商户订单号不能同时为空
     */
    @JsonProperty("trade_no")
    private String tradeNo;

    public AliTradePayQueryRequest(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

}
