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
import lombok.Data;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 *
 * <p>
 *
 * @author fenglei
 * @since 2019-06-10
 */
@Data
public class AliPayClient {

    private Logger logger = LoggerFactory.getLogger(AliPayClient.class);

    private AliPayConfig aliPayConfig;

    public AliPayClient(AliPayConfig aliPayConfig) {
        this.aliPayConfig = aliPayConfig;
    }

    public <T extends BaseResponse> T execute(AliRequest<T> request) throws IOException, PayException {
        String biz_content = JsonParser.modelToJSON(request).replaceAll("\r\n", "").replaceAll("\\s+", "");
        AliCommonRequest aliCommonRequest = new AliCommonRequest(
                aliPayConfig.getAppId(),
                request.getMethod(),
                request.getVersion(),
                aliPayConfig.getSignType().toString(),
                aliPayConfig.getNotifyUrl(),
                aliPayConfig.getReturnUrl(),
                biz_content);
        aliCommonRequest.setSign(getAliSign(aliCommonRequest));
        String requestStr = generateRequestParam(aliCommonRequest);
        String responseStr = HttpUtil.callPostStr(aliPayConfig.getRequestUrl(), requestStr, "form", null, null);

        // 返回结果验签
        if (!syncVerifySign(JSONObject.fromObject(responseStr), aliCommonRequest.getMethod())) {
            throw new PayException("返回报文签名不正确，疑似篡改！");
        }
        String content = JSONObject.fromObject(responseStr).get(aliCommonRequest.getMethod().replaceAll("\\.", "\\_") + "_response").toString();
        try {
            return JsonParser.JSONToModel(content, request.getResponseClass());
        } catch (IOException e) {
            throw new PayException(Standard.RET_FAIL, "");
        }
    }

    /**
     * 支付宝生成签名方法
     *
     * @param aliCommonRequest 支付宝请求参数
     * @return
     * @throws PayException
     */
    public String getAliSign(AliCommonRequest aliCommonRequest) throws PayException {
        // =========== 生成签名开始 ===========
        try {
            JSONObject requestJson = JSONObject.fromObject(JsonParser.modelToJSON(aliCommonRequest));
            String signContent = getSignContent(requestJson);
            logger.info("排序后的生成签名字段：" + signContent);
            // 生成签名
            String rsaSign = RSAUtil.rsaSign(signContent, aliPayConfig.getAppPrivateKey(), aliCommonRequest.getCharset());
            logger.info("生成签名：" + rsaSign);
            return rsaSign;
        } catch (Exception e) {
            throw new PayException(Standard.RET_FAIL, e.getMessage());
        }
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
     * 同步验签
     *
     * @param json
     * @return
     */
    public Boolean syncVerifySign(JSONObject json, String method) throws PayException {
        if (!json.containsKey("sign")) {
            throw new PayException("返回报文中不含签名");
        }
        String sign = json.get("sign").toString();
        logger.info("支付宝传来的签名：" + sign);
        json.remove("sign");
        String content = json.get(method.replaceAll("\\.", "\\_") + "_response").toString();
        logger.info("待验证返回签名内容：" + content);
        try {
            boolean flag = RSAUtil.rsaCheck(content, sign, aliPayConfig.getAliPublicKey(), Standard.ENCODING_UTF8, aliPayConfig.getSignType().toString());
            return flag;
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
            String value = (String) sortedParams.get(key);
            if (StringUtils.areNotEmpty(new String[]{key, value})) {
                content.append((index == 0 ? "" : "&") + key + "=" + value);
                ++index;
            }
        }
        return content.toString();
    }

}
