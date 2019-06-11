package com.viscum.pay.model.response.alipay.refund;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 退款使用的资金渠道
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TradeFundBill {
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
    /**
     * 渠道所使用的资金类型,目前只在资金渠道(fund_channel)是银行卡渠道(BANKCARD)的情况下才返回该信息(DEBIT_CARD:借记卡,CREDIT_CARD:信用卡,MIXED_CARD:借贷合一卡)
     */
    @JsonProperty("fund_type")
    private String fundType;
}
