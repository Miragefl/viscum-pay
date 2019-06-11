package com.viscum.pay.model.request.alipay.refund;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.viscum.pay.base.AliConstant;
import com.viscum.pay.model.request.alipay.AliRequest;
import com.viscum.pay.model.response.alipay.refund.AlipayTradeFastpayRefundQueryResponse;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 退款订单查询请求参数
 *
 * @author fenglei
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AlipayTradeFastpayRefundQueryRequest implements AliRequest<AlipayTradeFastpayRefundQueryResponse> {
    @JsonProperty("trade_no")
    private String tradeNo;
    @JsonProperty("out_trade_no")
    private String outTradeNo;
    @NotBlank
    @JsonProperty("out_request_no")
    private String outRequestNo;

    public AlipayTradeFastpayRefundQueryRequest(String tradeNo, String outTradeNo, @NotBlank String outRequestNo) {
        this.tradeNo = tradeNo;
        this.outTradeNo = outTradeNo;
        this.outRequestNo = outRequestNo;
    }

    @Override
    @JsonIgnore
    public String getVersion() {
        return "1.0";
    }

    @Override
    @JsonIgnore
    public String getMethod() {
        return AliConstant.REFUND_QUERY;
    }

    @Override
    @JsonIgnore
    public Class<AlipayTradeFastpayRefundQueryResponse> getResponseClass() {
        return AlipayTradeFastpayRefundQueryResponse.class;
    }
}
