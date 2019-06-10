/*
 * DefaultClient.java Copyright BrightStars Tech Co. Ltd. All Rights Reserved.
 */
package com.viscum.pay.client;

import com.viscum.pay.exception.PayException;
import com.viscum.pay.model.PayRequest;
import com.viscum.pay.model.PayResponse;

import java.io.IOException;

/**
 * <p>
 *
 * <p>
 *
 * @Author fenglei
 * @Date 2019-06-10
 */
public interface DefaultClient {

    //void setAliPayConfig(AliPayConfig config);

    <T extends PayResponse> T execute(PayRequest<T> request) throws IOException, PayException;

}
