/*
 * AliRequestTest.java Copyright BrightStars Tech Co. Ltd. All Rights Reserved.
 */
package com.viscum.pay;

import com.viscum.pay.base.AliConstant;
import com.viscum.pay.client.AliPayClient;
import com.viscum.pay.config.AliPayConfig;
import com.viscum.pay.enums.BillType;
import com.viscum.pay.enums.SignType;
import com.viscum.pay.exception.PayException;
import com.viscum.pay.model.request.alipay.bill.AlipayDataDataserviceBillDownloadurlQueryRequest;
import com.viscum.pay.model.request.alipay.pay.AlipayTradeAppPayRequest;
import com.viscum.pay.model.request.alipay.pay.AlipayTradeQueryRequest;
import com.viscum.pay.model.response.alipay.bill.AlipayDataDataserviceBillDownloadurlQueryResponse;
import org.apache.commons.lang.StringEscapeUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * <p>
 *
 * <p>
 *
 * @author fenglei
 * @since 2019-06-11
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AliRequestTest {

    @Resource
    private AliPayClient client;

    //支付宝沙箱账号
    String ali_app_id = "2016092500595365";
    //支付宝应用公钥
    String ali_app_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA3yPuzjAgV8dFtOBZzqKBdLWWHjb4TSo6hUb2rfn7It197FrwC4ZE+RxnuPUgPlkb1xYhMRZlhrRQTJuzv6KuzwfzergE9qcVpwQq2ifzDXkCXiwyQyeBsW/mohdYDZqsNFvcFTT5sye3vxYtjdlAuWFdHBCMNqv7nc0abTk6d1yveE4BpqJtaUm7QGAu91lmPYVl1Rt3Ba8KgTv08/OjODkuqhbri1xZNOejyhvYxXPmv2VK64Dd+nvWC3breRq1u+JyTZ9lECpXjx3X3inSebe10FvNcYUC09EPI8emy1xfR/KGjd4TlPIl3TGYm5iuUzqfmEXO6Vz5czm5QeJmswIDAQAB";
    //支付宝应用私钥
    String ali_app_private_key = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDfI+7OMCBXx0W04FnOooF0tZYeNvhNKjqFRvat+fsi3X3sWvALhkT5HGe49SA+WRvXFiExFmWGtFBMm7O/oq7PB/N6uAT2pxWnBCraJ/MNeQJeLDJDJ4Gxb+aiF1gNmqw0W9wVNPmzJ7e/Fi2N2UC5YV0cEIw2q/udzRptOTp3XK94TgGmom1pSbtAYC73WWY9hWXVG3cFrwqBO/Tz86M4OS6qFuuLXFk056PKG9jFc+a/ZUrrgN36e9YLdut5GrW74nJNn2UQKlePHdfeKdJ5t7XQW81xhQLT0Q8jx6bLXF9H8oaN3hOU8iXdMZibmK5TOp+YRc7pXPlzOblB4mazAgMBAAECggEBAMyBgjw2n23TlG9Rrv1SspLkWdEXUDpomXnMsAiDM0eg9+fG8HmfdVdaUTWScxqxUHKwNXtnq2TpnS6ZBQR9dPkJsHyoauQj/hQ0HZITSw7t+N2WY/poGtUkWZv1CR5uw6S5C9vj0XYA0Maw7M80bb15Hny6LcdtmA3oDHBOIueAtJVQMAT4oilBFuIRUlOLqHuFVUUir8qPFGk/I0q1oaafSDI3HyalOly692DuavXxmIa3H6TH7/u4dTPpIJnrXB8++9C5B1op79b2FNjFgxUjZ0/DzRrKwN+VHYgQGmD2zNNFka3H3YiMrBhoOvy+jUTumYxHa4hNYy8nZdaT5gECgYEA9PaIxIf4BPB0osL0vepdl2LWqa15gLperDql5C1BT3SiXQz9EXT+QYTJX1zh2HquxbwsNJoGjU0wBVMAg8zNv/UO7erWepLKqm8FK3JwFeob+DyidF1+Wn6pQr3I1W42VQlpbTiu8SR43U6BMaDmuWLFapBbloSENzcXnv72eusCgYEA6TGwwgH8wYZ1E/nFO/mgCTKYBeVh6DCWNAc4CnXZSAz2OiYJjDgsToHtoKRwXoR6DdCOcFoJtOgLGGho85gQckDqCswfsULtEV3a1ukXCySnE2txNk4pdHYRToZC3W38ysfvpDrAnLWUM0Jck9Rlz0gJVLHZnanAtJxgwGP0QVkCgYEAmEYEYL7w4wkiR+cOwlvE0A1FyXYnuGih30gZxUyIc77aCyO+0LI8Nzg3AYu4bJxetlYx2IUCm6WmuziMcmHGaJQKMsUvjERhrS6UNRzPJiBAQMBYcCOKHvDrgKx/AoWfSp8uJRH+VbJJW7CoSNRveEA43fRcqqU3lyctDM4LI/8CgYA3rcWmjRLsok4SFvEhANlVWN8ziTF3XyJ+Cbs3xCu9KFsiRzLAaCksFZnHYXvzFW6PHdzktpratwzvJv7oKsbCgP1g1pw6luBD5UT7S2xVHMtHt7+wV7lONWKsfSI5/Kj/Qf3WGLEkubZJBu9T3MLyWyDfOBfdrUp11WDEjxkjAQKBgQDqONjfw2vKbpfwLAcO2gE9SPvESeAEWivc2lX/Y9pNjgksFrz3JrXnSGNivU12AKpx6SePq3gJAiDm3Ft3Q2cShksS2VtU+AJ4yD1q/nITSHz3UPstdkxUNah1AMPBvzgx6lXpzmfcPD9jZP6AEH9UZvOkCUS4BLqYBqOlRekMCg==";
    //支付宝公钥
    String ali_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAryzAuA3/br4fJU3TIQb4Vc4xdile1BGxhtdo9d2Pj+mYIbaj5W+NsahvC4Do6kbtLAjm4nd9GdD89Rt76Mq+HXRxyj0gnLuGHSgHUACwRi6AZnW6LTM+s+HwdBCkVqS8fjnLe2Jc3OzucJ6dgBZ67glp1xE3TBxpW2frDgjT4QJMxRQZtcPmDyNmdeOp+vVcf/Pn3OVA5Z6xJg8ZcVj9aQQVi0U65HtBbNW+kDSuVYXPGY+TkJNmKjGdBP5yJc+RvOLGCPyC4gSStKFOYtbSWLsFj/kpu0wMWXd2C5rIZ+GdaQm4WZq6RrIkjw9Z/eje/OcXd+Is7+SVeIvchN/etwIDAQAB";
    //支付宝app支付回调地址
    String ali_pay_app_notify_url = "http://q2486265b4.qicp.vip:26054/userprofit/payorder/alicallback";
    //支付宝退款通知地址
    String ali_refund_notify_url = "http://q2486265b4.qicp.vip:26054/userprofit/refundorder/aliRefundCallBack";


    @Before
    public void init() {
        AliPayConfig config = new AliPayConfig(
                ali_app_id,
                ali_public_key,
                ali_app_private_key,
                ali_app_public_key,
                SignType.RSA2,
                ali_pay_app_notify_url,
                null,
                AliConstant.ALI_SERVER_URL);
        client.setAliPayConfig(config);
    }

    @Test
    public void billDownload() throws IOException, PayException {
        AlipayDataDataserviceBillDownloadurlQueryRequest request = new AlipayDataDataserviceBillDownloadurlQueryRequest(BillType.trade, "2019-05-26");
        AlipayDataDataserviceBillDownloadurlQueryResponse response = client.execute(request);
        System.out.println(response);
    }

    @Test
    public void payApp() throws PayException {
        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest(
                "body",
                "subject",
                "0.01",
                "201906120938551234"
        );
        client.execute(request);
    }

    @Test
    public void payQuery() throws IOException, PayException {
        AlipayTradeQueryRequest request = new AlipayTradeQueryRequest("48654654683545");
        client.execute(request);
    }


}
