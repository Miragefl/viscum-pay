package com.viscum.pay.model.request.wxpay.transfer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.viscum.pay.base.WxConstanst;
import com.viscum.pay.model.request.wxpay.WxRequest;
import com.viscum.pay.model.response.wxpay.transfer.WxBalanceTransferResponse;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WxBalanceTransferRequest implements WxRequest<WxBalanceTransferResponse> {
    /**
     * 必填
     * 申请商户号的appid或商户号绑定的appid
     */
    @JsonProperty("mch_appid")
    private String appId;
    /**
     * 必填
     * 微信支付分配的商户号
     */
    @JsonProperty("mchid")
    private String mchId;
    /**
     * 非必填
     * 微信支付分配的终端设备号
     */
    @JsonProperty("device_info")
    private String deviceInfo;
    /**
     * 必填
     * 随机字符串，不长于32位
     */
    @JsonProperty("nonce_str")
    private String nonceStr;
    /**
     * 签名
     */
    private String sign;
    /**
     * 商户订单号，需保持唯一性
     * (只能是字母或者数字，不能包含有其他字符)
     */
    @JsonProperty("partner_trade_no")
    private String partnerTradeNo;
    /**
     * 商户appid下，某用户的openid
     */
    @JsonProperty("openid")
    private String openId;
    /**
     * NO_CHECK：不校验真实姓名
     * FORCE_CHECK：强校验真实姓名
     */
    @JsonProperty("check_name")
    private String checkName;
    /**
     * 收款用户真实姓名。
     * 如果check_name设置为FORCE_CHECK，则必填用户真实姓名
     */
    @JsonProperty("re_user_name")
    private String reUserName;
    /**
     * 必填
     * 企业付款金额，单位为分
     */
    private String amount;
    /**
     * 必填
     * 企业付款备注。注意：备注中的敏感词会被转成字符*
     */
    private String desc;
    /**
     * 必填
     * 该IP同在商户平台设置的IP白名单中的IP没有关联，该IP可传用户端或者服务端的IP。
     */
    @JsonProperty("spbill_create_ip")
    private String spbillCreateIp;

    public WxBalanceTransferRequest(String partnerTradeNo, String openId, String checkName, String reUserName, String amount, String desc, String spbillCreateIp) {
        this.partnerTradeNo = partnerTradeNo;
        this.openId = openId;
        this.checkName = checkName;
        this.reUserName = reUserName;
        this.amount = amount;
        this.desc = desc;
        this.spbillCreateIp = spbillCreateIp;
    }

    @Override
    public Boolean needCert() {
        return true;
    }

    @Override
    public String getMethod() {
        return WxConstanst.BALANCE_TRANSFER;
    }

    @Override
    public Class<WxBalanceTransferResponse> getResponseClass() {
        return WxBalanceTransferResponse.class;
    }
}
