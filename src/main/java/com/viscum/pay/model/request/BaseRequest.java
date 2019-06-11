package com.viscum.pay.model.request;


import com.viscum.pay.model.response.BaseResponse;

/**
 * 支付宝基础请求参数
 *
 * @param <T>
 * @author fenglei
 */
public interface BaseRequest<T extends BaseResponse> {
    /**
     * 获取调用方法
     *
     * @return
     */
    String getMethod();

    /**
     * 获取返回类型
     *
     * @return
     */
    Class<T> getResponseClass();

}
