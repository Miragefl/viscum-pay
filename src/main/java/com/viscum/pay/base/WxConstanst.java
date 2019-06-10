package com.viscum.pay.base;

public class WxConstanst {

    /**
     * 微信预下单
     */
    public static final String UNIFIED_ORDER = "/pay/unifiedorder";
    /**
     * 支付订单查询
     */
    public static final String TRADE_QUERY = "/pay/orderquery";
    /**
     * 申请退款
     */
    public static final String TRADE_REFUND = "/secapi/pay/refund";
    /**
     * 退款查询
     */
    public static final String TRADE_REFUND_QUERY = "/pay/refundquery";
    /**
     * 账单下载
     */
    public static final String BILL_DOWNLOAD = "/pay/downloadbill";
    /**
     * 企业付款到余额
     */
    public static final String BALANCE_TRANSFER = "/mmpaymkttransfers/promotion/transfers";
    /**
     * 查询余额转账订单
     */
    public static final String BALANCE_TRANS_QUERY = "/mmpaymkttransfers/gettransferinfo";

    /**
     * 企业付款到银行卡
     */
    public static final String BANK_TRANSFER = "/mmpaysptrans/pay_bank";
    /**
     * 查询银行卡转账订单
     */
    public static final String BANK_TRANS_QUERY = "/mmpaysptrans/query_bank";
}
