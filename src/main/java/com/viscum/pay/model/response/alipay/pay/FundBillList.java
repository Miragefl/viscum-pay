package com.viscum.pay.model.response.alipay.pay;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 交易支付使用的资金渠道
 *
 * @author fenglei
 */
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

