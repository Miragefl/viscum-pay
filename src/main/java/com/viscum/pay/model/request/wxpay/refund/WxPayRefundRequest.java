package com.viscum.pay.model.request.wxpay.refund;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.viscum.pay.base.WxConstanst;
import com.viscum.pay.enums.SignType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.viscum.pay.model.request.wxpay.WxRequest;
import com.viscum.pay.model.response.wxpay.refund.WxPayRefundResponse;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WxPayRefundRequest implements WxRequest<WxPayRefundResponse> {
    /**
     * 公众账号ID
     */
    @JsonProperty("appid")
    private String appId;
    /**
     * 商户号
     */
    @JsonProperty("mch_id")
    private String mchId;
    /**
     * 随机字符串
     */
    @JsonProperty("nonce_str")
    private String nonceStr;
    /**
     * 签名
     */
    private String sign;
    /**
     * 签名类型
     */
    @JsonProperty("sign_type")
    private SignType signType = SignType.MD5;
    /**
     * 微信订单号(微信生成的订单号，在支付通知中有返回)
     */
    @JsonProperty("transaction_id")
    private String transactionId;
    /**
     * 商户订单号(商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一。)
     */
    @JsonProperty("out_trade_no")
    private String outTradeNo;
    /**
     * 商户退款单号(商户系统内部的退款单号，商户系统内部唯一，只能是数字、大小写字母_-|*@ ，同一退款单号多次请求只退一笔。)
     */
    @JsonProperty("out_refund_no")
    private String outRefundNo;
    /**
     * 订单金额(订单总金额，单位为分，只能为整数)
     */
    @Pattern(regexp = "(?!^0*(\\.0{1,2})?$)^\\d{1,13}$", message = "请输入正确的金额")
    @JsonProperty("total_fee")
    private String totalAmt;
    /**
     * (退款金额)退款总金额，订单总金额，单位为分，只能为整数
     */
    @Pattern(regexp = "(?!^0*(\\.0{1,2})?$)^\\d{1,13}$", message = "请输入正确的金额")
    @JsonProperty("refund_fee")
    private String refundAmt;
    /**
     * 退款结果通知url
     */
    @NotBlank
    @JsonProperty("notify_url")
    private String notifyUrl;

    public WxPayRefundRequest(String transactionId, String outTradeNo, String outRefundNo, String totalAmt, String refundAmt, String notifyUrl) {
        this.transactionId = transactionId;
        this.outTradeNo = outTradeNo;
        this.outRefundNo = outRefundNo;
        this.totalAmt = totalAmt;
        this.refundAmt = refundAmt;
        this.notifyUrl = notifyUrl;
    }

    @Override
    @JsonIgnore
    public Boolean needCert() {
        return true;
    }

    @Override
    @JsonIgnore
    public String getMethod() {
        return WxConstanst.TRADE_REFUND;
    }

    @Override
    @JsonIgnore
    public Class<WxPayRefundResponse> getResponseClass() {
        return WxPayRefundResponse.class;
    }
}
