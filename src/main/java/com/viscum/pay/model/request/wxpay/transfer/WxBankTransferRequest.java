package com.viscum.pay.model.request.wxpay.transfer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.viscum.pay.base.WxConstanst;
import com.viscum.pay.model.request.wxpay.WxRequest;
import com.viscum.pay.model.response.wxpay.transfer.WxBankTransferResponse;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WxBankTransferRequest implements WxRequest<WxBankTransferResponse> {
    /**
     * 微信支付分配的商户号
     */
    @JsonProperty("mchid")
    private String mchId;
    /**
     * 必填
     * 商户订单号，需保持唯一（只允许数字[0~9]或字母[A~Z]和[a~z]，最短8位，最长32位）
     */
    @JsonProperty("partner_trade_no")
    private String partnerTradeNo;
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
     * 必填
     * 收款方银行卡号（采用标准RSA算法，公钥由微信侧提供
     */
    @JsonProperty("enc_bank_no")
    private String encBankNo;
    /**
     * 必填
     * 收款方用户名（采用标准RSA算法，公钥由微信侧提供）
     */
    @JsonProperty("enc_true_name")
    private String encTrueName;
    /**
     * 必填
     * 银行卡所在开户行编号
     */
    @JsonProperty("bank_code")
    private String bankCode;
    /**
     * 必填
     * 付款金额：RMB分（支付总额，不含手续费）
     */
    private String amount;
    /**
     * 企业付款到银行卡付款说明,即订单备注
     */
    private String desc;


    @Override
    @JsonIgnore
    public Boolean needCert() {
        return true;
    }

    @Override
    @JsonIgnore
    public String getMethod() {
        return WxConstanst.BANK_TRANSFER;
    }

    @Override
    @JsonIgnore
    public Class<WxBankTransferResponse> getResponseClass() {
        return WxBankTransferResponse.class;
    }
}
