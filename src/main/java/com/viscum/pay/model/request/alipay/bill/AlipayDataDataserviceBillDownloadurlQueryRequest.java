package com.viscum.pay.model.request.alipay.bill;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.viscum.pay.base.AliConstant;
import com.viscum.pay.enums.BillType;
import com.viscum.pay.model.request.alipay.AliRequest;
import com.viscum.pay.model.response.alipay.bill.AlipayDataDataserviceBillDownloadurlQueryResponse;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 查询账单地址请求参数
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AlipayDataDataserviceBillDownloadurlQueryRequest implements AliRequest<AlipayDataDataserviceBillDownloadurlQueryResponse> {

    /**
     * 账单类型，商户通过接口或商户经开放平台授权后其所属服务商通过接口可以获取以下账单类型：trade、signcustomer；
     * trade指商户基于支付宝交易收单的业务账单；
     * signcustomer是指基于商户支付宝余额收入及支出等资金变动的帐务账单；
     */
    @JsonProperty("bill_type")
    private BillType billType;
    /**
     * 账单时间：日账单格式为yyyy-MM-dd，月账单格式为yyyy-MM。
     */
    @NotBlank
    @JsonProperty("bill_date")
    private String billDate;

    public AlipayDataDataserviceBillDownloadurlQueryRequest(BillType billType, String billDate) {
        this.billType = billType;
        this.billDate = billDate;
    }

    @Override
    @JsonIgnore
    public String getVersion() {
        return "1.0";
    }


    @Override
    @JsonIgnore
    public String getMethod() {
        return AliConstant.BILL_DOWNLOAD;
    }

    @Override
    @JsonIgnore
    public Class<AlipayDataDataserviceBillDownloadurlQueryResponse> getResponseClass() {
        return AlipayDataDataserviceBillDownloadurlQueryResponse.class;
    }
}
