package com.viscum.pay.model.response.alipay.transfer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.viscum.pay.model.response.BaseResponse;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AlipayFundTransToaccountTransferResponse extends BaseResponse {
    /**
     * 网关返回码
     */
    private String code;
    /**
     * 网关返回码描述
     */
    private String msg;
    /**
     * 业务返回码
     */
    @JsonProperty("sub_code")
    private String subCode;
    /**
     * 业务返回码描述
     */
    @JsonProperty("sub_msg")
    private String subMsg;
    /**
     * 签名
     */
    private String sign;
    /**
     * 商户转账唯一订单号：发起转账来源方定义的转账单据号。请求时对应的参数，原样返回。
     */
    @JsonProperty("out_biz_no")
    private String outBizNo;
    /**
     * 支付宝转账单据号，成功一定返回，失败可能不返回也可能返回。
     */
    @JsonProperty("order_id")
    private String orderId;
    /**
     * 支付时间：格式为yyyy-MM-dd HH:mm:ss，仅转账成功返回。
     */
    @JsonProperty("pay_date")
    private String payDate;
}
