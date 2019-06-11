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
public class WxBankTransferQueryResponse extends BaseResponse {
    @XmlElement(name = "return_code")
    private String returnCode;
    @XmlElement(name = "return_msg")
    private String returnMsg;
    @XmlElement(name = "result_code")
    private String resultVode;
    @XmlElement(name = "err_code")
    private String errCode;
    @XmlElement(name = "err_code_des")
    private String errCodeDes;
    @XmlElement(name = "mch_id")
    private String mchId;
    @XmlElement(name = "partner_trade_no")
    private String partnerTradeNo;
    @XmlElement(name = "payment_no")
    private String paymentNo;
    @XmlElement(name = "bank_no_md5")
    private String bankNoMd5;
    @XmlElement(name = "true_name_md5")
    private String trueNameMd5;
    private String amount;
    private String status;
    @XmlElement(name = "cmms_amt")
    private String cmmsAmt;
    @XmlElement(name = "create_time")
    private String createTime;
    @XmlElement(name = "pay_succ_time")
    private String paySuccTime;
    private String reason;

}
