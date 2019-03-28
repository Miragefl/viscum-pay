package com.viscum.pay.model.response.alipay;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RefundRoyaltyResult {
    /**
     * 退分账金额
     */
    @JsonProperty("refund_amount")
    private String refundAmount;
    /**
     * 分账类型.
     * 普通分账为：transfer;
     * 补差为：replenish;
     * 为空默认为分账transfer;
     */
    @JsonProperty("royalty_type")
    private String royaltyType;
    /**
     * 退分账结果码
     */
    @JsonProperty("result_code")
    private String resultCode;
    /**
     * 转出人支付宝账号对应用户ID
     */
    @JsonProperty("trans_out")
    private String transOut;
    /**
     * 转出人支付宝账号
     */
    @JsonProperty("trans_out_email")
    private String transOutEmail;
    /**
     * 转入人支付宝账号对应用户ID
     */
    @JsonProperty("trans_in")
    private String transIn;
    /**
     * 转入人支付宝账号
     */
    @JsonProperty("trans_in_email")
    private String transInEmail;
}

