package com.viscum.pay.model.response.alipay.bill;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.viscum.pay.model.response.BaseResponse;
import lombok.Data;

/**
 * 查询下载账单地址
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AliBillDownloadUrlQueryResponse extends BaseResponse {
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
     * 账单下载地址链接，获取连接后30秒后未下载，链接地址失效
     */
    @JsonProperty("bill_download_url")
    private String billDownloadUrl;

}
