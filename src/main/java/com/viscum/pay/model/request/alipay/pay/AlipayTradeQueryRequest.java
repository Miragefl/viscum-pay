package com.viscum.pay.model.request.alipay.pay;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.viscum.pay.base.AliConstant;
import com.viscum.pay.model.request.alipay.AliRequest;
import com.viscum.pay.model.response.alipay.pay.AlipayTradeQueryResponse;
import lombok.Data;

/**
 * 支付订单查询请求参数
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AlipayTradeQueryRequest implements AliRequest<AlipayTradeQueryResponse> {
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

    public AlipayTradeQueryRequest(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    @Override
    public String getVersion() {
        return "1.0";
    }

    @Override
    public String getMethod() {
        return AliConstant.PAY_QUERY;
    }

    @Override
    public Class<AlipayTradeQueryResponse> getResponseClass() {
        return AlipayTradeQueryResponse.class;
    }
}
