package com.viscum.pay.model.response.alipay.refund;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.viscum.pay.model.response.BaseResponse;
import lombok.Data;

import java.util.List;

/**
 * 退款查询响应参数
 *
 * @author fenglei
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AlipayTradeFastpayRefundQueryResponse extends BaseResponse {
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
     * 创建交易传入的商户订单号
     */
    @JsonProperty("out_trade_no")
    private String outTradeNo;
    /**
     * 本笔退款对应的退款请求号
     */
    @JsonProperty("out_request_no")
    private String outRequestNo;
    /**
     * 发起退款时，传入的退款原因
     */
    @JsonProperty("refund_reason")
    private String refundReason;
    /**
     * 该笔退款所对应的交易的订单金额
     */
    @JsonProperty("total_amount")
    private String totalAmount;
    /**
     * 本次退款请求，对应的退款金额
     */
    @JsonProperty("refund_amount")
    private String refundAmount;
    /**
     * 退分账明细信息
     */
    @JsonProperty("refund_royaltys")
    private List<RefundRoyaltyResult> refundRoyaltys;
    /**
     * 退款时间；
     * 默认不返回该信息，需与支付宝约定后配置返回；
     */
    @JsonProperty("gmt_refund_pay")
    private String gmtRefundPay;
    /**
     * 本次退款使用的资金渠道；
     * 默认不返回该信息，需与支付宝约定后配置返回；
     */
    @JsonProperty("refund_detail_item_list")
    private List<TradeFundBill> refundDetailItemList;
    /**
     * 本次商户实际退回金额；
     * 默认不返回该信息，需与支付宝约定后配置返回
     */
    @JsonProperty("send_back_fee")
    private String sendBackFee;
    /**
     * 本次退款针对收款方的退收费金额；
     * 默认不返回该信息，需与支付宝约定后配置返回；
     */
    @JsonProperty("refund_charge_amount")
    private String refundChargeAmount;
    /**
     * 退款清算编号，用于清算对账使用；
     * 只在银行间联交易场景下返回该信息；
     */
    @JsonProperty("refund_settlement_id")
    private String refundSettlementId;
    /**
     * 本次退款金额中买家退款金额
     */
    @JsonProperty("present_refund_buyer_amount")
    private String presentRefundBuyerAmount;
    /**
     * 本次退款金额中平台优惠退款金额
     */
    @JsonProperty("present_refund_discount_amount")
    private String presentRefundDiscountAmount;
    /**
     * 本次退款金额中商家优惠退款金额
     */
    @JsonProperty("present_refund_mdiscount_amount")
    private String presentRefundMdiscountAmount;
}
