package com.viscum.pay.model.response.wxpay.transfer;

import com.viscum.pay.model.response.BaseResponse;
import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class WxBankTransferResponse extends BaseResponse {
    @XmlElement(name = "return_code")
    private String returnCode;
    @XmlElement(name = "return_msg")
    private String returnMsg;
    @XmlElement(name = "result_code")
    private String resultCode;
    @XmlElement(name = "err_code")
    private String errCode;
    @XmlElement(name = "err_code_des")
    private String errCodeDes;
    @XmlElement(name = "mch_id")
    private String mchId;
    @XmlElement(name = "partner_trade_no")
    private String partnerTradeNo;
    private String amount;
    @XmlElement(name = "nonce_str")
    private String nonceStr;
    private String sign;
    @XmlElement(name = "payment_no")
    private String paymentNo;
    @XmlElement(name = "cmms_amt")
    private String cmmsAmt;

}
