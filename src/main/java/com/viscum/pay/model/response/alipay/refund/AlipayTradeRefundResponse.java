package com.viscum.pay.model.response.alipay.refund;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.viscum.pay.model.response.BaseResponse;
import lombok.Data;

import java.util.List;

/**
 * 支付宝退款
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AlipayTradeRefundResponse extends BaseResponse {
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
     * 商户订单号
     */
    @JsonProperty("out_trade_no")
    private String outTradeNo;
    /**
     * 用户的登录id
     */
    @JsonProperty("buyer_logon_id")
    private String buyerLogonId;
    /**
     * 本次退款是否发生了资金变化
     */
    @JsonProperty("fund_change")
    private String fundChange;
    /**
     * 退款总金额
     */
    @JsonProperty("refund_fee")
    private String refundFee;
    /**
     * 退款币种信息
     */
    @JsonProperty("refund_currency")
    private String refundCurrency;
    /**
     * 退款支付时间
     */
    @JsonProperty("gmt_refund_pay")
    private String gmtRefundPay;
    /**
     * 退款使用的资金渠道
     */
    @JsonProperty("refund_detail_item_list")
    private List<TradeFundBill> refundDetailItemList;
    /**
     * 交易在支付时候的门店名称
     */
    @JsonProperty("store_name")
    private String storeName;
    /**
     * 买家在支付宝的用户id
     */
    @JsonProperty("buyer_user_id")
    private String buyerUserId;
    /**
     * 退回的前置资产列表
     */
    @JsonProperty("refund_preset_paytool_list")
    private List<PresetPayToolInfo> refundPresetPaytoolList;
    /**
     * 本次退款针对收款方的退收费金额；
     * 默认不返回该信息，需与支付宝约定后配置返回
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

