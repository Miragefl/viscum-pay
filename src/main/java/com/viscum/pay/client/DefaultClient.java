/*
 * DefaultClient.java Copyright BrightStars Tech Co. Ltd. All Rights Reserved.
 */
package com.viscum.pay.client;

import com.viscum.pay.exception.PayException;
import com.viscum.pay.model.request.BaseRequest;
import com.viscum.pay.model.response.BaseResponse;

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

    <T extends BaseResponse> T execute(BaseRequest<T> request) throws IOException, PayException;

}
