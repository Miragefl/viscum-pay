package com.viscum.pay.model.request.wxpay;

import com.viscum.pay.base.WxConstanst;
import com.viscum.pay.enums.SignType;
import com.viscum.pay.enums.TradeType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.viscum.pay.model.PayRequest;
import com.viscum.pay.model.response.wxpay.WxTradePayResponse;
import lombok.Data;

import javax.validation.constraints.*;


/**
 * 微信预下单参数
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WxTradePayRequest implements PayRequest<WxTradePayResponse> {

    /**
     * 签名方式
     */
    @JsonProperty("sign_type")
    private SignType signType = SignType.MD5;

    /**
     * 商品简单描述，该字段请按照规范传递
     */
    @NotBlank
    @JsonProperty("body")
    private String body;
    /**
     * 商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|* 且在同一个商户号下唯一
     */
    @NotNull
    @JsonProperty("out_trade_no")
    private String outTradeNo;

    /**
     * 订单总金额，单位为分
     */
    @JsonProperty("total_fee")
    private String totalAmt;
    /**
     * 异步接收微信支付结果通知的回调地址，通知url必须为外网可访问的url，不能携带参数
     */
    @JsonProperty("notify_url")
    private String notifyUrl;
    /**
     * 交易类型
     */
    @JsonProperty("trade_type")
    private TradeType tradeType = TradeType.JSAPI;
    /**
     * 终端IP
     * 支持IPV4和IPV6两种格式的IP地址。调用微信支付API的机器IP
     */
    @JsonProperty("spbill_create_ip")
    private String spbillCreateIp;

    public WxTradePayRequest(String body, String outTradeNo, String totalAmt, String notifyUrl, String spbillCreateIp) {
        this.body = body;
        this.outTradeNo = outTradeNo;
        this.totalAmt = totalAmt;
        this.notifyUrl = notifyUrl;
        this.spbillCreateIp = spbillCreateIp;
    }

    public WxTradePayRequest(String body, String outTradeNo, String totalAmt, String notifyUrl, String spbillCreateIp, TradeType tradeType) {
        this.body = body;
        this.outTradeNo = outTradeNo;
        this.totalAmt = totalAmt;
        this.notifyUrl = notifyUrl;
        this.tradeType = tradeType;
        this.spbillCreateIp = spbillCreateIp;
    }

    public WxTradePayRequest(String body, String outTradeNo, String totalAmt, String notifyUrl, String spbillCreateIp, TradeType tradeType, SignType signType) {
        this(body, outTradeNo, totalAmt, notifyUrl, spbillCreateIp, tradeType);
        this.signType = signType;
    }

    @Override
    public Boolean needCert() {
        return false;
    }

    @Override
    public String getMethod() {
        return WxConstanst.UNIFIED_ORDER;
    }

    @Override
    public String getServerUrl() {
        return WxConstanst.SERVER_URL;
    }

    @Override
    public Class<WxTradePayResponse> getResponseClass() {
        return WxTradePayResponse.class;
    }


}
