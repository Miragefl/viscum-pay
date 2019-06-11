package com.viscum.pay.model.response.wxpay.pay;

import com.viscum.pay.model.response.BaseResponse;
import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 微信订单查询
 */
@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "xml")
public class WxTradePayQueryResponse extends BaseResponse {
    /**
     * 返回状态码
     */
    @XmlElement(name = "return_code")
    private String returnCode;
    /**
     * 返回信息
     */
    @XmlElement(name = "return_msg")
    private String returnMsg;
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
     * 设备号
     */
    @XmlElement(name = "device_info")
    private String deviceInfo;
    /**
     * 用户标识
     */
    @XmlElement(name = "openid")
    private String openId;
    /**
     * 是否关注公众账号
     */
    @XmlElement(name = "is_subscribe")
    private String isSubscribe;
    /**
     * 交易类型
     */
    @XmlElement(name = "trade_type")
    private String tradeType;
    /**
     * 交易状态
     */
    @XmlElement(name = "trade_state")
    private String tradeStatus;
    /**
     * 付款银行
     */
    @XmlElement(name = "bank_type")
    private String bankType;
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
     * 微信支付订单号
     */
    @XmlElement(name = "transaction_id")
    private String transactionId;
    /**
     * 商户订单号
     */
    @XmlElement(name = "out_trade_no")
    private String outTradeNo;
    /**
     * 附加数据
     */
    private String attach;
    /**
     * 支付完成时间
     */
    @XmlElement(name = "time_end")
    private String timeEnd;
    /**
     * 交易状态描述
     */
    @XmlElement(name = "trade_state_desc")
    private String tradeStateDesc;

}
