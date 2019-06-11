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
public class WxBalanceTransferQueryResponse extends BaseResponse {

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
    @XmlElement(name = "partner_trade_no")
    private String partnerTradeNo;
    @XmlElement(name = "appid")
    private String appId;
    @XmlElement(name = "mch_id")
    private String mchId;
    @XmlElement(name = "detail_id")
    private String detailId;
    private String status;
    private String reason;
    @XmlElement(name = "openid")
    private String openId;
    @XmlElement(name = "transfer_name")
    private String transferName;
    @XmlElement(name = "payment_amount")
    private String paymentAmount;
    @XmlElement(name = "transfer_time")
    private String transferTime;
    @XmlElement(name = "payment_time")
    private String paymentTime;
    private String desc;

}
