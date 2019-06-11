package com.viscum.pay.model.request.alipay.transfer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.viscum.pay.base.AliConstant;
import com.viscum.pay.model.request.alipay.AliRequest;
import com.viscum.pay.model.response.alipay.transfer.AlipayFundTransOrderQueryResponse;
import lombok.Data;

/**
 * 支付宝转账查询接口
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AlipayFundTransOrderQueryRequest implements AliRequest<AlipayFundTransOrderQueryResponse> {

    @JsonProperty("out_biz_no")
    private String outBizNo;
    @JsonProperty("order_id")
    private String orderId;

    public AlipayFundTransOrderQueryRequest(String outBizNo, String orderId) {
        this.outBizNo = outBizNo;
        this.orderId = orderId;
    }

    public AlipayFundTransOrderQueryRequest(String outBizNo) {
        this.outBizNo = outBizNo;
    }

    @Override
    @JsonIgnore
    public String getVersion() {
        return "1.0";
    }

    @Override
    @JsonIgnore
    public String getMethod() {
        return AliConstant.TRANSFER_QUERY;
    }

    @Override
    @JsonIgnore
    public Class<AlipayFundTransOrderQueryResponse> getResponseClass() {
        return AlipayFundTransOrderQueryResponse.class;
    }
}
