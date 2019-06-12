/*
 * AliPayClient.java Copyright BrightStars Tech Co. Ltd. All Rights Reserved.
 */
package com.viscum.pay.client;

import com.viscum.pay.base.Standard;
import com.viscum.pay.config.AliPayConfig;
import com.viscum.pay.exception.PayException;
import com.viscum.pay.model.request.alipay.AliRequest;
import com.viscum.pay.model.response.BaseResponse;
import com.viscum.pay.model.request.alipay.AliCommonRequest;
import com.viscum.pay.util.*;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import static com.viscum.pay.util.RsaUtil.rsaCheck;

/**
 * <p>
 * 支付宝客户端
 * <p>
 *
 * @author fenglei
 * @since 2019-06-10
 */
@Component
public class AliPayClient {

    private Logger logger = LoggerFactory.getLogger(AliPayClient.class);

    private final String HTTP_STR = "http";

    private AliPayConfig aliPayConfig;

    public void setAliPayConfig(AliPayConfig aliPayConfig) {
        this.aliPayConfig = aliPayConfig;
    }

    public <T extends BaseResponse> T execute(AliRequest<T> request) throws PayException {
        try {
            // 生成请求参数
            String requestStr = generateCommonRequest(request);
            logger.info("请求支付宝参数:{}", requestStr);
            // 调用支付宝接口
            byte[] bytes = HttpUtil.callPostStr(aliPayConfig.getRequestUrl(), requestStr, "form", null, null);
            String responseStr = new String(bytes, Standard.ENCODING_UTF8);
            // 对返回报文验签 返回业务结果
            JSONObject content = syncVerifySign(responseStr, request.getMethod());
            return JsonParser.jsonToModel(content.toString(), request.getResponseClass());
        } catch (IOException e) {
            throw new PayException(Standard.RET_FAIL, "");
        }
    }

    public String generateCommonRequest(AliRequest request) throws IOException, PayException {
        String bizContent = JsonParser.modelToJSON(request).replaceAll("\r\n", "").replaceAll("\\s+", "");
        // 生成支付宝请求报文实体
        AliCommonRequest aliCommonRequest = new AliCommonRequest(
                aliPayConfig.getAppId(),
                request.getMethod(),
                aliPayConfig.getSignType(),
                request.getVersion(),
                aliPayConfig.getNotifyUrl(),
                aliPayConfig.getReturnUrl(),
                bizContent);
        // 生成签名
        String sign = getAliSign(aliCommonRequest);
        aliCommonRequest.setSign(sign);
        return generateRequestParam(aliCommonRequest);
    }

    /**
     * 生成摘要后地址
     *
     * @param aliCommonRequest 支付宝请求参数
     * @return
     * @throws PayException
     */
    public String generateRequestParam(AliCommonRequest aliCommonRequest) throws PayException {
        // 生成摘要后的地址
        StringBuilder sb = new StringBuilder();
        try {
            JSONObject requestJson = JSONObject.fromObject(JsonParser.modelToJSON(aliCommonRequest));
            logger.info("requestJson:\n{}", requestJson);
            List<String> keys = new ArrayList(requestJson.keySet());
            for (int i = 0; i < keys.size(); i++) {
                sb.append("&" + keys.get(i) + "=" + URLEncoder.encode(requestJson.getString(keys.get(i)), aliCommonRequest.getCharset()));
            }
            return sb.substring(1);
        } catch (UnsupportedEncodingException e) {
            logger.info("支付宝签名URLEncode出错", e);
            throw new PayException(Standard.RET_FAIL, e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    /**
     * 支付宝生成签名方法
     *
     * @param aliCommonRequest 支付宝请求参数
     * @return
     * @throws PayException
     */
    public String getAliSign(AliCommonRequest aliCommonRequest) throws PayException {
        try {
            JSONObject requestJson = JSONObject.fromObject(JsonParser.modelToJSON(aliCommonRequest));
            String signContent = getSignContent(requestJson);
            logger.info("排序后的生成签名字段：" + signContent);
            // 生成签名
            String rsaSign = RsaUtil.rsaSign(signContent, aliPayConfig.getAppPrivateKey(), aliCommonRequest.getCharset(), aliPayConfig.getSignType().toString());
            logger.info("生成签名：" + rsaSign);
            return rsaSign;
        } catch (Exception e) {
            throw new PayException(Standard.RET_FAIL, e.getMessage());
        }
    }


    /**
     * 同步验签
     *
     * @param responseStr
     * @param method
     * @return
     * @throws PayException
     */
    public JSONObject syncVerifySign(String responseStr, String method) throws PayException {
        try {
            // 获取签名
            JSONObject responseJson = JSONObject.fromObject(responseStr);
            String sign = responseJson.getString("sign");
            JSONObject content = responseJson.getJSONObject(method.replaceAll("\\.", "\\_") + "_response");
            Iterator<String> it = content.keys();
            JSONObject signJson = new JSONObject();
            while (it.hasNext()) {
                String key = it.next();
                String value = "";
                if (!(content.get(key) instanceof String)) {
                    value = "\"" + content.get(key).toString() + "\"";
                } else {
                    value = content.getString(key);
                }
                signJson.put(key, value);
            }
            String signContent = signJson.toString();
            logger.info("支付宝传来的签名：" + sign);
            if (signContent.contains(HTTP_STR)) {
                signContent = signContent.replaceAll("/", "\\\\/");
            }
            logger.info("待验证返回签名内容：" + signContent);
            if (!rsaCheck(signContent, sign, aliPayConfig.getAliPublicKey(), Standard.ENCODING_UTF8, aliPayConfig.getSignType().toString())) {
                throw new PayException("");
            }
            return content;
        } catch (PayException e) {
            logger.info("验签出错", e);
            throw new PayException(Standard.RET_FAIL, e.getErrMsg());
        }
    }

    /**
     * 生成待签名报文
     *
     * @param sortedParams
     * @return
     */
    public static String getSignContent(JSONObject sortedParams) {
        StringBuilder content = new StringBuilder();
        List<String> keys = new ArrayList(sortedParams.keySet());
        Collections.sort(keys);
        int index = 0;
        for (int i = 0; i < keys.size(); ++i) {
            String key = keys.get(i);
            String value = String.valueOf(sortedParams.get(key));
            if (BaseStringUtils.areNotEmpty(new String[]{key, value})) {
                content.append((index == 0 ? "" : "&") + key + "=" + value);
                ++index;
            }
        }
        return content.toString();
    }

}
