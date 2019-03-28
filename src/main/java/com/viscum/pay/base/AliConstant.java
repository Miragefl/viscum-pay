package com.viscum.pay.base;

public class AliConstant {

        public static final String ALI_SERVER_URL = "https://openapi.alipay.com/gateway.do";
//    public static final String ALI_SERVER_URL = "https://openapi.alipaydev.com/gateway.do";
    // 支付宝方法定义
    // app支付
    public static final String UNIFIED_ORDER = "alipay.trade.app.pay";
    // 支付订单查询
    public static final String TRADE_QUERY = "alipay.trade.query";
    // 退款
    public static final String TRADE_REFUND = "alipay.trade.refund";
    // 退款查询
    public static final String TRADE_REFUND_QUERY = "alipay.trade.fastpay.refund";
    // 单笔转账到支付宝账户
    public static final String ACCOUNT_TRANSFER = "alipay.fund.trans.toaccount.transfer";
    // 查询转账订单
    public static final String TRADE_TRANS_QUERY = "alipay.fund.trans.toaccount.transfer";
    // 查询对账单下载地址
    public static final String BILL_DOWNLOAD = "alipay.data.dataservice.bill.downloadurl.query";
}
