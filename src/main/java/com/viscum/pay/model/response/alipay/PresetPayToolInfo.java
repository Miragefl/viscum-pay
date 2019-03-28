package com.viscum.pay.model.response.alipay;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 退回的前置资产列表
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PresetPayToolInfo {
    /**
     * 前置资产金额
     */
    private String amount;
    /**
     * 前置资产类型编码，和收单支付传入的preset_pay_tool里面的类型编码保持一致。
     */
    @JsonProperty("assert_type_code")
    private String assertTypeCode;
}
