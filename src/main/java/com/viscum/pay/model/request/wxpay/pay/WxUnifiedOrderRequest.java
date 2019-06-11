package com.viscum.pay.model.request.wxpay.pay;

import com.viscum.pay.base.WxConstanst;
import com.viscum.pay.enums.SignType;
import com.viscum.pay.enums.TradeType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.viscum.pay.model.request.wxpay.WxRequest;
import com.viscum.pay.model.response.wxpay.pay.WxUnifiedOrderResponse;
import lombok.Data;

import javax.validation.constraints.*;


/**
 * 微信预下单参数
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WxUnifiedOrderRequest implements WxRequest<WxUnifiedOrderResponse> {
    /**
     * 应用ID
     */
    @JsonProperty("appid")
    private String appId;
    /**
     * 商户号
     */
    @JsonProperty("mch_id")
    private String mchId;
    /**
     * 随机字符串，不长于32位
     */
    @JsonProperty("nonce_str")
    private String nonceStr;
    /**
     * 签名
     */
    private String sign;
    /**
     * 签名方式
     */
    @JsonProperty("sign_type")
    private SignType signType = SignType.MD5;

    /**
     * 商品简单描述，该字段请按照规范传递
     */
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
    private String tradeType = TradeType.APP.toString();

    @JsonProperty("open_id")
    private String openId;
    /**
     * 终端IP
     * 支持IPV4和IPV6两种格式的IP地址。调用微信支付API的机器IP
     */
    @JsonProperty("spbill_create_ip")
    private String spbillCreateIp;

    public WxUnifiedOrderRequest(String body, String outTradeNo, String totalAmt, String notifyUrl, String spbillCreateIp) {
        this.body = body;
        this.outTradeNo = outTradeNo;
        this.totalAmt = totalAmt;
        this.notifyUrl = notifyUrl;
        this.spbillCreateIp = spbillCreateIp;
    }

    public WxUnifiedOrderRequest(String body, String outTradeNo, String totalAmt, String notifyUrl, String spbillCreateIp, String tradeType, String openId) {
        this.body = body;
        this.outTradeNo = outTradeNo;
        this.totalAmt = totalAmt;
        this.notifyUrl = notifyUrl;
        this.tradeType = tradeType;
        if (tradeType.equals(TradeType.JSAPI.toString()))
            this.openId = openId;
        this.spbillCreateIp = spbillCreateIp;
    }

    public WxUnifiedOrderRequest(String body, String outTradeNo, String totalAmt, String notifyUrl, String spbillCreateIp, String tradeType, String openId, SignType signType) {
        this(body, outTradeNo, totalAmt, notifyUrl, spbillCreateIp, tradeType, openId);
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
    public Class<WxUnifiedOrderResponse> getResponseClass() {
        return WxUnifiedOrderResponse.class;
    }
}
