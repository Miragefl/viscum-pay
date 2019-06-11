package com.viscum.pay.model.request;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.viscum.pay.model.response.BaseResponse;

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
