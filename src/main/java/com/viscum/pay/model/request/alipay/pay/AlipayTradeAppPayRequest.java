package com.viscum.pay.model.request.alipay.pay;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.viscum.pay.base.AliConstant;
import com.viscum.pay.model.request.alipay.AliRequest;
import com.viscum.pay.model.response.alipay.pay.AlipayTradePayResponse;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * App支付请求参数
 * @author fenglei
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AlipayTradeAppPayRequest implements AliRequest<AlipayTradePayResponse> {
    /**
     * 对一笔交易的具体描述信息。如果是多种商品，请将商品描述字符串累加传给body。
     */
    @NotBlank
    private String body;
    /**
     * 商品的标题/交易标题/订单标题/订单关键字等。
     */
    @NotBlank
    private String subject;
    /**
     * 该笔订单允许的最晚付款时间，逾期将关闭交易。取值范围：1m～15d。m-分钟，h-小时，d-天，1c-当天（1c-当天的情况下，无论交易何时创建，都在0点关闭）。 该参数数值不接受小数点， 如 1.5h，可转换为 90m。
     * 注：若为空，则默认为15d。
     */
    @JsonProperty("time_out_express")
    private String timeoutExpress;
    /**
     * 订单总金额，单位为元，精确到小数点后两位，取值范围[0.01,100000000]
     */
    @JsonProperty("total_amount")
    private String totalAmt;
    /**
     * 销售产品码，商家和支付宝签约的产品码，为固定值QUICK_MSECURITY_PAY
     */
    @JsonProperty("product_code")
    private String productCode = "QUICK_MSECURITY_PAY";
    /**
     * 商户网站唯一订单号
     */
    @JsonProperty("out_trade_no")
    private String outTradeNo;

    public AlipayTradeAppPayRequest(String body, String subject, String totalAmt,  String outTradeNo, String timeoutExpress) {
        this(body, subject, totalAmt, outTradeNo);
        this.timeoutExpress = timeoutExpress;
    }

    public AlipayTradeAppPayRequest(String body, String subject, String totalAmt,  String outTradeNo) {
        this.body = body;
        this.subject = subject;
        this.totalAmt = totalAmt;
        this.outTradeNo = outTradeNo;
    }

    @Override
    @JsonIgnore
    public String getVersion() {
        return "1.0";
    }

    @Override
    @JsonIgnore
    public String getMethod() {
        return AliConstant.PAY_APP;
    }

    @Override
    @JsonIgnore
    public Class<AlipayTradePayResponse> getResponseClass() {
        return AlipayTradePayResponse.class;
    }
}
