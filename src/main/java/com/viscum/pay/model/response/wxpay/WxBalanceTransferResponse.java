package com.viscum.pay.model.response.wxpay;

import com.viscum.pay.model.PayResponse;
import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class WxBalanceTransferResponse extends PayResponse {
    @XmlElement(name = "return_code")
    private String returnCode;
    @XmlElement(name = "return_msg")
    private String returnMsg;
    @XmlElement(name = "mch_appid")
    private String appId;
    @XmlElement(name = "mchid")
    private String mchId;
    @XmlElement(name = "device_info")
    private String deviceInfo;
    @XmlElement(name = "nonce_str")
    private String nonceStr;
    @XmlElement(name = "result_code")
    private String resultCode;
    @XmlElement(name = "err_code")
    private String errCode;
    @XmlElement(name = "err_code_des")
    private String errCodeDes;
    @XmlElement(name = "partner_trade_no")
    private String partnerTradeNo;
    @XmlElement(name = "payment_no")
    private String paymentNo;
    @XmlElement(name = "payment_time")
    private String paymentTime;

}
