/*
 * WxGetpublickeyRequest.java Copyright BrightStars Tech Co. Ltd. All Rights Reserved.
 */
package com.viscum.pay.model.response.wxpay.risk;

import com.viscum.pay.model.response.BaseResponse;

/**
 * <p>
 *
 * <p>
 *
 * @author fenglei
 * @since 2019-05-13
 */
public class WxGetpublickeyResponse extends BaseResponse {
    /**
     * 微信支付分配的商户号
     */
    private String mch_id;
    /**
     * RSA 公钥
     */
    private String pub_key;

}
