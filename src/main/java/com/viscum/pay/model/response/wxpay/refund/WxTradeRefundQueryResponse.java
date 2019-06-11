package com.viscum.pay.model.response.wxpay.refund;

import com.viscum.pay.model.response.BaseResponse;
import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "xml")
public class WxTradeRefundQueryResponse extends BaseResponse {
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
    @XmlElement(name = "appid")
    private String appId;
    @XmlElement(name = "mch_id")
    private String mchId;
    @XmlElement(name = "nonce_str")
    private String nonceStr;
    @XmlElement(name = "sign")
    private String sign;
    @XmlElement(name = "total_refund_count")
    private String totalRefundCount;
    @XmlElement(name = "transaction_id")
    private String transactionId;
    @XmlElement(name = "out_trade_no")
    private String outTradeNo;
    @XmlElement(name = "total_fee")
    private String totalFee;
    @XmlElement(name = "settlement_total_fee")
    private String settlementTotalFee;
    @XmlElement(name = "fee_type")
    private String feeType;
    @XmlElement(name = "cash_fee")
    private String cashFee;
    @XmlElement(name = "refund_count")
    private String refundCount;
    @XmlElement(name = "out_refund_no")
    private String outRefundNo;
    @XmlElement(name = "refund_id")
    private String refundId;
    @XmlElement(name = "refund_channel")
    private String refundChannel;
    @XmlElement(name = "refund_fee")
    private String refundFee;
    @XmlElement(name = "settlement_refund_fee")
    private String settlementRefundFee;
    @XmlElement(name = "refund_status")
    private String refundStatus;
    @XmlElement(name = "refund_account")
    private String refundAccount;
    @XmlElement(name = "refund_recv_accout")
    private String refundRecvAccout;
    @XmlElement(name = "refund_success_time")
    private String refundSuccessTime;

}
