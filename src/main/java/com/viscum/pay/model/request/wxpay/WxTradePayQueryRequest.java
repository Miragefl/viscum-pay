package com.viscum.pay.model.request.wxpay;

import com.viscum.pay.base.WxConstanst;
import com.viscum.pay.enums.SignType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.viscum.pay.model.PayRequest;
import com.viscum.pay.model.response.wxpay.WxTradePayQueryResponse;
import com.viscum.pay.util.WxPayX509TrustManager;
import lombok.Data;


/**
 * 微信订单查询
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WxTradePayQueryRequest implements PayRequest<WxTradePayQueryResponse> {
    /**
     * 应用APPID
     */
    @JsonProperty("appid")
    private String appId;
    /**
     * 商户号
     */
    @JsonProperty("mch_id")
    private String mchId;
    /**
     * 微信订单号(微信的订单号，优先使用)
     */
    @JsonProperty("transaction_id")
    private String transactionId;
    /**
     * 商户订单号(商户系统内部的订单号，当没提供transaction_id时需要传这个。)
     */
    @JsonProperty("out_trade_no")
    private String outTradeNo;
    /**
     * 随机字符串
     */
    @JsonProperty("nonce_str")
    private String nonceStr;
    /**
     * 签名
     */
    private String sign;
    @JsonProperty("sign_type")
    private SignType signType = SignType.MD5;

    public WxTradePayQueryRequest(String transactionId, String outTradeNo) {
        this.transactionId = transactionId;
        this.outTradeNo = outTradeNo;
    }

    public WxTradePayQueryRequest(String transactionId, String outTradeNo, SignType signType) {
        this(transactionId, outTradeNo);
        this.signType = signType;
    }


    @Override
    public Boolean needCert() {
        return false;
    }

    @Override
    public String getMethod() {
        return WxConstanst.TRADE_QUERY;
    }

    @Override
    public String getServerUrl() {
        return WxConstanst.SERVER_URL;
    }

    @Override
    public Class<WxTradePayQueryResponse> getResponseClass() {
        return WxTradePayQueryResponse.class;
    }
}
