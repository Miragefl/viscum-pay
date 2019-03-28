package com.viscum.pay.model.request.alipay;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 退款订单查询请求参数
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AliTradeRefundQueryRequest {
    @JsonProperty("trade_no")
    private String tradeNo;
    @JsonProperty("out_trade_no")
    private String outTradeNo;
    @NotBlank
    @JsonProperty("out_request_no")
    private String outRequestNo;

    public AliTradeRefundQueryRequest(String tradeNo, String outTradeNo, @NotBlank String outRequestNo) {
        this.tradeNo = tradeNo;
        this.outTradeNo = outTradeNo;
        this.outRequestNo = outRequestNo;
    }
}
