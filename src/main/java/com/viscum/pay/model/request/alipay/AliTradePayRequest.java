package com.viscum.pay.model.request.alipay;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * 支付请求参数
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AliTradePayRequest {
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
    @Pattern(regexp = "(?!^0*(\\.0{1,2})?$)^\\d{1,13}(\\.\\d{1,2})?$", message = "请输入正确的金额")
    @JsonProperty("total_amount")
    private String totalAmt;
    /**
     * 销售产品码，商家和支付宝签约的产品码，为固定值QUICK_MSECURITY_PAY
     */
    @NotBlank
    @JsonProperty("product_code")
    private String productCode;
    /**
     * 商户网站唯一订单号
     */
    @NotBlank
    @JsonProperty("out_trade_no")
    private String outTradeNo;

    public AliTradePayRequest(String body, String subject, String totalAmt, String productCode, String outTradeNo, String timeoutExpress) {
        this(body, subject, totalAmt, productCode, outTradeNo);
        this.timeoutExpress = timeoutExpress;
    }

    public AliTradePayRequest(String body, String subject, String totalAmt, String productCode, String outTradeNo) {
        this.body = body;
        this.subject = subject;
        this.totalAmt = totalAmt;
        this.productCode = productCode;
        this.outTradeNo = outTradeNo;
    }
}
