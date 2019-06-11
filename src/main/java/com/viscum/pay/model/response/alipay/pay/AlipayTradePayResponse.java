package com.viscum.pay.model.response.alipay.pay;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.viscum.pay.model.response.BaseResponse;
import lombok.Data;

/**
 * 支付宝预下单响应参数
 *
 * @author fenglei
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AlipayTradePayResponse extends BaseResponse {

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
