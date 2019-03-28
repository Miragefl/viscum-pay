package com.viscum.pay.model.request.alipay;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 支付宝转账查询接口
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AliTransferQueryRequest {

    @JsonProperty("out_biz_no")
    private String outBizNo;
    @JsonProperty("order_id")
    private String orderId;

    public AliTransferQueryRequest(String outBizNo, String orderId) {
        this.outBizNo = outBizNo;
        this.orderId = orderId;
    }
}
