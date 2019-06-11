/*
 * WxRequest.java Copyright BrightStars Tech Co. Ltd. All Rights Reserved.
 */
package com.viscum.pay.model.request.alipay;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.viscum.pay.model.request.BaseRequest;
import com.viscum.pay.model.response.BaseResponse;

/**
 * <p>
 *
 * <p>
 *
 * @Author fenglei
 * @Date 2019-06-11
 */
public interface AliRequest<T extends BaseResponse> extends BaseRequest<T> {
    /**
     * 获取接口版本
     *
     * @return
     */
    String getVersion();
}
