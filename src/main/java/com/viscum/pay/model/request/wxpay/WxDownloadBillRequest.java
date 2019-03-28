package com.viscum.pay.model.request.wxpay;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.viscum.pay.base.WxConstanst;
import com.viscum.pay.model.PayRequest;
import com.viscum.pay.model.response.wxpay.WxDownloadBillResponse;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WxDownloadBillRequest implements PayRequest<WxDownloadBillResponse> {
//    @JsonProperty("appid")
//    private String appId;
//    @JsonProperty("mch_id")
//    private String mchId;
//    @JsonProperty("nonce_str")
//    private String nonceStr;
//    private String sign;
    /**
     * 账单类型
     * ALL（默认值），返回当日所有订单信息（不含充值退款订单）
     * <p>
     * SUCCESS，返回当日成功支付的订单（不含充值退款订单）
     * <p>
     * REFUND，返回当日退款订单（不含充值退款订单）
     * <p>
     * RECHARGE_REFUND，返回当日充值退款订单
     */
    @JsonProperty("bill_type")
    private String billType;
    /**
     * 下载对账单的日期，格式：20140603
     */
    @NotBlank
    @JsonProperty("bill_date")
    private String billDate;
    /**
     * 压缩账单(非必传参数，固定值：GZIP，返回格式为.gzip的压缩包账单。不传则默认为数据流形式)
     */
    @JsonProperty("tar_type")
    private String tarType;

    public WxDownloadBillRequest(String billDate) {
        this.billDate = billDate;
    }

    @Override
    public Boolean needCert() {
        return true;
    }

    @Override
    public String getMethod() {
        return WxConstanst.BILL_DOWNLOAD;
    }

    @Override
    public String getServerUrl() {
        return WxConstanst.SERVER_URL;
    }

    @Override
    public Class<WxDownloadBillResponse> getResponseClass() {
        return WxDownloadBillResponse.class;
    }
}
