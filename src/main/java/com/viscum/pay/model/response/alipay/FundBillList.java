package com.viscum.pay.model.response.alipay;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class FundBillList {
    /**
     * 交易使用的资金渠道
     */
    @JsonProperty("fund_channel")
    private String fundChannel;
    /**
     * 该支付工具类型所使用的金额
     */
    private String amount;
    /**
     * 渠道实际付款金额
     */
    @JsonProperty("real_amount")
    private String realAmount;
}

