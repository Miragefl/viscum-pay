package com.viscum.pay;

import com.viscum.pay.client.WxPayClient;
import com.viscum.pay.config.WxPayConfig;
import com.viscum.pay.exception.PayException;
import com.viscum.pay.model.request.wxpay.pay.WxUnifiedOrderRequest;
import com.viscum.pay.model.response.wxpay.pay.WxUnifiedOrderResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * <p>
 *
 * <p>
 *
 * @author fenglei
 * @since 2019-06-12
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class WxClientTest {

    String wxAppId = "wx78f59cc86439488e";
    String wxAppSecret = "6d52efea570a808c20d329a36de17b62";
    String wxMchId = "1532224691";
    String wxMchKey = "SJDK12304SDJALqi1g223n11al1QODZA";
    String wxKeyPath = "apiclient_cert.p12";
    String wxNotifyUrl = "http://q2486265b4.qicp.vip:26054/userprofit/payorder/wxcallback";
    //    String wx_public_key = "nMIIBCgKCAQEAzXH5ERiULvdtgRgI+AVGHETIuHx+hV+kXg6/W+EhVq0AbDx04eMVC5d9xRDPC45wqzecOfJ237GRT054phyl3w+joNZFu/tmpPMivRZduXjWtkxW2l563II8w0k072e9BU1WnAznSzb9QG0WdLepJK5Uv5iqiFOrQA/a5VfmhPEviSZ/O5y0ARgcEWKm/snwdzy0YgQRlb1Wp+Yv8/L349lA9KAMcSSdSOYLJk337e51HHrubEKYr4xfFsEGq/rgI/MmIx9kUkkZMe02lQnQIzoL2Bmx3UR2crHamyloD263GBPhXgavPzQMjsjxHDg/2EId2oxfIieDFsyA9iJRuwIDAQAB";
    String requestUrl = "https://api.mch.weixin.qq.com";

    @Autowired
    private WxPayClient client;


    @Before
    public void init() {
        WxPayConfig config = new WxPayConfig(wxAppId, wxAppSecret, wxMchId, wxMchKey, wxKeyPath, requestUrl);
        client.setWxPayConfig(config);
    }

    @Test
    public void payApp() throws PayException {
        WxUnifiedOrderRequest request = new WxUnifiedOrderRequest("body", "201906121818171234", "1", wxNotifyUrl, "192.168.1.1");
        WxUnifiedOrderResponse response = client.execute(request);
        System.out.println(response);
    }
}
