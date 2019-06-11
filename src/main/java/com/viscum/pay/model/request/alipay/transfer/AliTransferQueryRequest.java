package com.viscum.pay.model.request.alipay.transfer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.viscum.pay.base.AliConstant;
import com.viscum.pay.model.request.alipay.AliRequest;
import com.viscum.pay.model.response.alipay.transfer.AliTransferQueryResponse;
import lombok.Data;

/**
 * 支付宝转账查询接口
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AliTransferQueryRequest implements AliRequest<AliTransferQueryResponse> {

    @JsonProperty("out_biz_no")
    private String outBizNo;
    @JsonProperty("order_id")
    private String orderId;

    public AliTransferQueryRequest(String outBizNo, String orderId) {
        this.outBizNo = outBizNo;
        this.orderId = orderId;
    }

    public AliTransferQueryRequest(String outBizNo) {
        this.outBizNo = outBizNo;
    }

    @Override
    public String getVersion() {
        return "1.0";
    }

    @Override
    public String getMethod() {
        return AliConstant.TRANSFER_QUERY;
    }

    @Override
    public Class<AliTransferQueryResponse> getResponseClass() {
        return AliTransferQueryResponse.class;
    }
}
