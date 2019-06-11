package com.viscum.pay.model.request;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.viscum.pay.model.response.BaseResponse;

public interface BaseRequest<T extends BaseResponse> {
    @JsonIgnore
    String getMethod();
    @JsonIgnore
    Class<T> getResponseClass();

}
