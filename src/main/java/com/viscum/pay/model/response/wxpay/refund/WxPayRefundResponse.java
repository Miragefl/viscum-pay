package com.viscum.pay.model.response.wxpay.refund;

import com.viscum.pay.model.response.BaseResponse;
import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 微信退款响应参数
 *
 * @author fenglei
 */
@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "xml")
public class WxPayRefundResponse extends BaseResponse {
    /**
     * 业务结果(SUCCESS/FAIL
     * SUCCESS退款申请接收成功，结果通过退款查询接口查询
     * FAIL 提交业务失败)
     */
    @XmlElement(name = "return_code")
    private String returnCode;
    /**
     * 错误代码
     */
    @XmlElement(name = "return_msg")
    private String returnMsg;
    /**
     * 业务结果
     */
    @XmlElement(name = "result_code")
    private String resultCode;
    /**
     * 错误代码
     */
    @XmlElement(name = "err_code")
    private String errCode;
    /**
     * 错误代码描述
     */
    @XmlElement(name = "err_code_des")
    private String errCodeDes;
    /**
     * 公众账号ID
     */
    @XmlElement(name = "appid")
    private String appId;
    /**
     * 商户号
     */
    @XmlElement(name = "mch_id")
    private String mchId;
    /**
     * 随机字符串
     */
    @XmlElement(name = "nonce_str")
    private String nonceStr;
    /**
     * 签名
     */
    private String sign;
    /**
     * 微信订单号
     */
    @XmlElement(name = "transaction_id")
    private String transactionId;
    /**
     * 商户订单号
     */
    @XmlElement(name = "out_trade_no")
    private String outTradeNo;
    /**
     * 商户退款单号
     */
    @XmlElement(name = "out_refund_no")
    private String outRefundNo;
    /**
     * 微信退款单号
     */
    @XmlElement(name = "refund_id")
    private String refundId;
    /**
     * 退款金额
     */
    @XmlElement(name = "refund_fee")
    private String refundFee;
    /**
     * 应结退款金额
     */
    @XmlElement(name = "settlement_refund_fee")
    private String settlementRefundFee;
    /**
     * 标价金额
     */
    @XmlElement(name = "total_fee")
    private String totalFee;
    /**
     * 应结订单金额
     */
    @XmlElement(name = "settlement_total_fee")
    private String settlementTotalFee;
    /**
     * 标价币种
     */
    @XmlElement(name = "fee_type")
    private String feeType;
    /**
     * 现金支付金额
     */
    @XmlElement(name = "cash_fee")
    private String cashFee;
    /**
     * 现金支付币种
     */
    @XmlElement(name = "cash_fee_type")
    private String cashFeeType;
    /**
     * 现金退款金额
     */
    @XmlElement(name = "cash_refund_fee")
    private String cashRefundFee;

}
