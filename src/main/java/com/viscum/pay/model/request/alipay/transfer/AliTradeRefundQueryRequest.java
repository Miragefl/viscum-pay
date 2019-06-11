package com.viscum.pay.model.request.alipay.transfer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.viscum.pay.base.AliConstant;
import com.viscum.pay.model.request.alipay.AliRequest;
import com.viscum.pay.model.response.alipay.refund.AliTradeRefundQueryResponse;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 退款订单查询请求参数
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AliTradeRefundQueryRequest implements AliRequest<AliTradeRefundQueryResponse> {
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

    @Override
    public String getVersion() {
        return "1.0";
    }

    @Override
    public String getMethod() {
        return AliConstant.REFUND_QUERY;
    }

    @Override
    public Class<AliTradeRefundQueryResponse> getResponseClass() {
        return AliTradeRefundQueryResponse.class;
    }
}
