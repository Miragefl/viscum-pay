package com.viscum.pay.model;

import com.viscum.pay.util.WxPayX509TrustManager;

public interface PayRequest<T extends PayResponse> {

    public Boolean needCert();

    public String getMethod();

    public String getServerUrl();

    public Class<T> getResponseClass();
}
