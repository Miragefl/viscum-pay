package com.viscum.pay.model.response.alipay;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AliTradeAppPayResponse {

    /**
     * 请求地址
     */
    private String requestUrl;
    /**
     * 请求参数
     */
    private String params;
    /**
     * 完整地址
     */
//    private String orderStr;

}
