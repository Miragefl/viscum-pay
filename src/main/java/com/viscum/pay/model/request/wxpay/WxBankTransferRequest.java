package com.viscum.pay.model.request.wxpay;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.viscum.pay.base.WxConstanst;
import com.viscum.pay.model.PayRequest;
import com.viscum.pay.model.response.wxpay.WxBankTransferQueryResponse;
import com.viscum.pay.model.response.wxpay.WxBankTransferResponse;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WxBankTransferRequest implements PayRequest<WxBankTransferResponse> {
    //    private String sign;
//    @JsonProperty("mchid")
//    private String mchId;
    @JsonProperty("partner_trade_no")
    private String partnerTradeNo;
    //    @JsonProperty("nonce_str")
//    private String nonceStr;
    @JsonProperty("enc_bank_no")
    private String encBankNo;
    @JsonProperty("enc_true_name")
    private String encTrueName;
    @JsonProperty("bank_code")
    private String bankCode;
    private String amount;
    private String desc;


    @Override
    public Boolean needCert() {
        return true;
    }

    @Override
    public String getMethod() {
        return WxConstanst.BANK_TRANSFER;
    }

    @Override
    public String getServerUrl() {
        return WxConstanst.SERVER_URL;
    }

    @Override
    public Class<WxBankTransferResponse> getResponseClass() {
        return WxBankTransferResponse.class;
    }
}
