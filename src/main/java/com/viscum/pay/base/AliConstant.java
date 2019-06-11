package com.viscum.pay.base;

public class AliConstant {

    public static final String ALI_SERVER_URL = "https://openapi.alipay.com/gateway.do";

    //    public static final String ALI_SERVER_URL = "https://openapi.alipaydev.com/gateway.do";
    // 支付宝方法定义
    // app支付
    public static final String PAY_APP = "alipay.trade.app.pay";
    // H5支付
    public static final String PAY_WAP = "alipay.trade.wap.pay";
    // 支付订单查询
    public static final String PAY_QUERY = "alipay.trade.query";
    // 退款
    public static final String REFUND = "alipay.trade.refund";
    // 退款查询
    public static final String REFUND_QUERY = "alipay.trade.fastpay.refund.query";
    // 单笔转账到支付宝账户
    public static final String TRANSFER_TOACCOUNT = "alipay.fund.trans.toaccount.transfer";
    // 查询转账订单
    public static final String TRANSFER_QUERY = "alipay.fund.trans.order.query";
    // 查询对账单下载地址
    public static final String BILL_DOWNLOAD = "alipay.data.dataservice.bill.downloadurl.query";

}
