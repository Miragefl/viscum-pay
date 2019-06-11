/*
 * AlipayTradeWapPayRequest.java Copyright BrightStars Tech Co. Ltd. All Rights Reserved.
 */
package com.viscum.pay.model.request.alipay.pay;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.viscum.pay.base.AliConstant;
import com.viscum.pay.model.request.alipay.AliRequest;
import com.viscum.pay.model.response.alipay.pay.AlipayTradePayResponse;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * <p>
 *
 * @author fenglei
 * @since 2019-05-18
 */
@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AlipayTradeWapPayRequest implements AliRequest<AlipayTradePayResponse> {
    /**
     * 非必传
     * 对一笔交易的具体描述信息。如果是多种商品，请将商品描述字符串累加传给body。
     */
    private String body;

    /**
     * 必传
     * 商品的标题/交易标题/订单标题/订单关键字等。
     */
    private String subject;

    /**
     * 必传
     * 商户网站唯一订单号
     */
    @JsonProperty("out_trade_no")
    private String outTradeNo;

    /**
     * 非必传
     * 该笔订单允许的最晚付款时间，逾期将关闭交易。取值范围：1m～15d。m-分钟，h-小时，d-天，1c-当天（1c-当天的情况下，无论交易何时创建，都在0点关闭）。
     * 该参数数值不接受小数点， 如 1.5h，可转换为 90m。注：若为空，则默认为15d。
     */
    @JsonProperty("timeout_express")
    private String timeoutExpress;

    /**
     * 非必传
     * 绝对超时时间，格式为yyyy-MM-dd HH:mm。 注：1）以支付宝系统时间为准；2）如果和timeout_express参数同时传入，以time_expire为准。
     */
    @JsonProperty("time_expire")
    private String timeExpire;

    /**
     * 必传
     * 订单总金额，单位为元，精确到小数点后两位，取值范围[0.01,100000000]
     */
    @JsonProperty("total_amount")
    private String totalAmount;

    /**
     * 必传
     * 销售产品码，商家和支付宝签约的产品码。该产品请填写固定值：QUICK_WAP_WAY
     */
    @JsonProperty("product_code")
    private String productCode = "QUICK_WAP_WAY";

    /**
     * 非必传
     * 商品主类型：0—虚拟类商品，1—实物类商品注：虚拟类商品不支持使用花呗渠道
     */
    @JsonProperty("goods_type")
    private String goodsType;

    /**
     * 非必传
     * 公用回传参数，如果请求时传递了该参数，则返回给商户时会回传该参数。支付宝会在异步通知时将该参数原样返回。本参数必须进行UrlEncode之后才可以发送给支付宝
     */
    @JsonProperty("passback_params")
    private String passbackParams;

    /**
     * 非必传
     * 惠参数注：仅与支付宝协商后可用
     */
    @JsonProperty("promo_params")
    private String promoParams;

    /**
     * 非必传
     * 业务扩展参数
     */
    @JsonProperty("extend_params")
    private String extendParams;

    /**
     * 非必传
     * 可用渠道，用户只能在指定渠道范围内支付当有多个渠道时用“,”分隔
     * 注：与disable_pay_channels互斥
     */
    @JsonProperty("enable_pay_channels")
    private String enablePayChannels;
    /**
     * 非必传
     * 禁用渠道，用户不可用指定渠道支付当有多个渠道时用“,”分隔
     * 注：与enable_pay_channels互斥
     */
    @JsonProperty("disable_pay_channels")
    private String disablePayChannels;
    /**
     * 非必传
     * 商户门店编号。该参数用于请求参数中以区分各门店，非必传项
     */
    @JsonProperty("store_id")
    private String storeId;
    /**
     * 非必传
     * 添加该参数后在h5支付收银台会出现返回按钮，可用于用户付款中途退出并返回到该参数指定的商户网站地址。注：该参数对支付宝钱包标准收银台下的跳转不生效
     */
    @JsonProperty("quit_url")
    private String quitUrl;
    /**
     * 非必传
     * 外部指定买家
     */
    @JsonProperty("ext_user_info")
    private String extUserInfo;

    public AlipayTradeWapPayRequest(String subject, String outTradeNo, String totalAmount) {
        this.subject = subject;
        this.outTradeNo = outTradeNo;
        this.totalAmount = totalAmount;
    }

    @Override
    @JsonIgnore
    public String getVersion() {
        return "1.0";
    }

    @Override
    @JsonIgnore
    public String getMethod() {
        return AliConstant.PAY_WAP;
    }

    @Override
    @JsonIgnore
    public Class<AlipayTradePayResponse> getResponseClass() {
        return AlipayTradePayResponse.class;
    }
}
