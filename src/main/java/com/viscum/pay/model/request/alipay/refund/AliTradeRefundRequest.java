package com.viscum.pay.model.request.alipay.refund;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.viscum.pay.base.AliConstant;
import com.viscum.pay.model.request.alipay.AliRequest;
import com.viscum.pay.model.response.alipay.refund.AliTradeRefundResponse;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * 支付宝退款请求参数
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AliTradeRefundRequest implements AliRequest<AliTradeRefundResponse> {
    @JsonProperty("out_trade_no")
    private String outTradeNo;

    @JsonProperty("trade_no")
    private String tradeNo;

    @Pattern(regexp = "(?!^0*(\\.0{1,2})?$)^\\d{1,13}(\\.\\d{1,2})?$", message = "请输入正确的金额")
    @JsonProperty("refund_amount")
    private String refundAmt;

    @NotBlank
    @JsonProperty("outRequestNo")
    private String outRequestNo;

    public AliTradeRefundRequest(String outTradeNo, String tradeNo, @Pattern(regexp = "(?!^0*(\\.0{1,2})?$)^\\d{1,13}(\\.\\d{1,2})?$", message = "请输入正确的金额") String refundAmt, @NotBlank String outRequestNo) {
        this.outTradeNo = outTradeNo;
        this.tradeNo = tradeNo;
        this.refundAmt = refundAmt;
        this.outRequestNo = outRequestNo;
    }

    @Override
    public String getVersion() {
        return "1.0";
    }

    @Override
    public String getMethod() {
        return AliConstant.REFUND;
    }

    @Override
    public Class<AliTradeRefundResponse> getResponseClass() {
        return AliTradeRefundResponse.class;
    }
}