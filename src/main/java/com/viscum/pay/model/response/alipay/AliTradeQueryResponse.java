package com.viscum.pay.model.response.alipay;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * 查询支付订单
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class AliTradeQueryResponse {
    /**
     * 网关返回码
     */
    private String code;
    /**
     * 网关返回码描述
     */
    private String msg;
    /**
     * 业务返回码
     */
    @JsonProperty("sub_code")
    private String subCode;
    /**
     * 业务返回码描述
     */
    @JsonProperty("sub_msg")
    private String subMsg;
    /**
     * 签名
     */
    private String sign;
    /**
     * 支付宝交易号
     */
    @JsonProperty("trade_no")
    private String tradeNo;
    /**
     * 商家订单号
     */
    @JsonProperty("out_trade_no")
    private String outTradeNo;
    /**
     * 买家支付宝账号
     */
    @JsonProperty("buyerLogonId")
    private String buyer_logon_id;
    /**
     * 交易状态：WAIT_BUYER_PAY（交易创建，等待买家付款）、TRADE_CLOSED（未付款交易超时关闭，或支付完成后全额退款）、TRADE_SUCCESS（交易支付成功）、TRADE_FINISHED（交易结束，不可退款）
     */
    @JsonProperty("trade_status")
    private String tradeStatus;
    /**
     * 交易的订单金额，单位为元，两位小数
     */
    @JsonProperty("total_amount")
    private String totalAmount;
    /**
     * 标价币种
     */
    @JsonProperty("trans_currency")
    private String transCurrency;
    /**
     * 订单结算币种
     */
    @JsonProperty("settle_currency")
    private String settleCurrency;
    /**
     * 结算币种订单金额
     */
    @JsonProperty("settleAmount")
    private String settle_amount;
    /**
     * 订单支付币种
     */
    @JsonProperty("payCurrency")
    private String pay_currency;
    /**
     * 支付币种订单金额
     */
    @JsonProperty("pay_amount")
    private String payAmount;
    /**
     * 结算币种兑换标价币种汇率
     */
    @JsonProperty("settle_trans_rate")
    private String settleTransRate;
    /**
     * 标价币种兑换支付币种汇率
     */
    @JsonProperty("trans_pay_rate")
    private String transPayRate;
    /**
     * 买家实付金额，单位为元
     */
    @JsonProperty("buyer_pay_amount")
    private String buyerPayAmount;
    /**
     * 积分支付的金额，单位为元
     */
    @JsonProperty("point_amount")
    private String pointAmount;
    /**
     * 交易中用户支付的可开具发票的金额，单位为元，两位小数
     */
    @JsonProperty("invoice_amount")
    private String invoiceAmount;
    /**
     * 本次交易打款给卖家的时间
     */
    @JsonProperty("send_pay_date")
    private String sendPayDate;
    /**
     * 实收金额
     */
    @JsonProperty("receipt_amount")
    private String receiptAmount;
    /**
     * 商户门店编号
     */
    @JsonProperty("store_id")
    private String storeId;
    /**
     * 商户机具终端编号
     */
    @JsonProperty("terminal_id")
    private String terminalId;
    /**
     * 交易支付使用的资金渠道
     */
    @JsonProperty("fund_bill_list")
    private List<FundBillList> fundBillList;
    /**
     * 请求交易支付中的商户店铺的名称
     */
    @JsonProperty("store_name")
    private String storeName;
    /**
     * 买家在支付宝的用户id
     */
    @JsonProperty("buyer_user_id")
    private String buyerUserId;
    /**
     * 该笔交易针对收款方的收费金额；
     * 默认不返回该信息，需与支付宝约定后配置返回
     */
    @JsonProperty("charge_amount")
    private String chargeAmount;
    /**
     * 费率活动标识，当交易享受活动优惠费率时，返回该活动的标识；
     * 默认不返回该信息，需与支付宝约定后配置返回；
     */
    @JsonProperty("charge_flags")
    private String chargeFlags;
    /**
     * 支付清算编号，用于清算对账使用；
     * 只在银行间联交易场景下返回该信息；
     */
    @JsonProperty("settlement_id")
    private String settlementId;
    /**
     * 预授权支付模式，该参数仅在信用预授权支付场景下返回。信用预授权支付：CREDIT_PREAUTH_PAY
     */
    @JsonProperty("auth_trade_pay_mode")
    private String authTradePayMode;
    /**
     * 买家用户类型。CORPORATE:企业用户；PRIVATE:个人用户
     */
    @JsonProperty("buyer_user_type")
    private String buyerUserType;
    /**
     * 商家优惠金额
     */
    @JsonProperty("mdiscount_amount")
    private String mdiscountAmount;
    /**
     * 平台优惠金额
     */
    @JsonProperty("discount_amount")
    private String discountAmount;
    /**
     * 买家名称；
     * 买家为个人用户时为买家姓名，买家为企业用户时为企业名称；
     * 默认不返回该信息，需与支付宝约定后配置返回；
     */
    @JsonProperty("buyer_user_name")
    private String buyerUserName;
    /**
     * 订单标题；
     * 只在间连场景下返回；
     */
    private String subject;
    /**
     * 订单描述;
     * 只在间连场景下返回；
     */
    private String body;
    /**
     * 间连商户在支付宝端的商户编号；
     * 只在间连场景下返回；
     */
    @JsonProperty("alipay_sub_merchant_id")
    private String alipaySubMerchantId;

}
