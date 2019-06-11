package com.viscum.pay.model.request;


import com.viscum.pay.model.response.BaseResponse;

public interface BaseRequest<T extends BaseResponse> {

    String getMethod();

    Class<T> getResponseClass();

}
