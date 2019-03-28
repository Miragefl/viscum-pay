package com.viscum.pay.util;

import com.viscum.pay.exception.PayException;
import com.viscum.pay.base.Standard;
import com.viscum.pay.config.AliPayConfig;
import com.viscum.pay.model.request.alipay.AliCommonParam;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;

import java.io.*;
import java.net.URLEncoder;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.*;

@Slf4j
public class AliPayUtil {

    /**
     * 生成摘要后地址
     *
     * @param commonRequest
     * @return
     * @throws PayException
     */
    public static String destUrl(AliCommonParam commonRequest, AliPayConfig config) throws PayException {
        // 生成摘要后的地址
        StringBuilder sb = new StringBuilder();
        try {
            log.info("========alipay_appid========" + commonRequest.getAppId());
            sb.append("app_id=" + URLEncoder.encode(commonRequest.getAppId(), Standard.ENCODING_UTF8));
            log.info("========biz_content========" + commonRequest.getBizContent());
            sb.append("&biz_content=" + URLEncoder.encode(commonRequest.getBizContent(), Standard.ENCODING_UTF8));
            sb.append("&charset=" + URLEncoder.encode(commonRequest.getCharset(), Standard.ENCODING_UTF8));
            sb.append("&format=" + URLEncoder.encode(commonRequest.getFormat(), Standard.ENCODING_UTF8));
            log.info("========method========" + commonRequest.getMethod());
            sb.append("&method=" + URLEncoder.encode(commonRequest.getMethod(), Standard.ENCODING_UTF8));
            log.info("========notifyUrl========" + commonRequest.getNotifyUrl());
            sb.append("&notify_url=" + URLEncoder.encode(commonRequest.getNotifyUrl(), Standard.ENCODING_UTF8));
            sb.append("&sign_type=" + URLEncoder.encode(commonRequest.getSignType(), Standard.ENCODING_UTF8));
            log.info("========timestamp========" + commonRequest.getTimestamp());
            sb.append("&timestamp=" + URLEncoder.encode(commonRequest.getTimestamp(), Standard.ENCODING_UTF8));
            sb.append("&version=" + URLEncoder.encode(commonRequest.getVersion(), Standard.ENCODING_UTF8));
            sb.append("&sign=" + URLEncoder.encode(commonRequest.getSign(), Standard.ENCODING_UTF8));
            log.info("摘要后url：" + sb.toString().trim());
        } catch (UnsupportedEncodingException e) {
            log.info("支付宝签名URLEncode出错", e);
            throw new PayException(Standard.RET_FAIL, e.getMessage());
        }
        return sb.toString();
    }

    /**
     * 支付宝生成签名方法
     *
     * @param commonRequest
     * @param config
     * @return
     * @throws PayException
     */
    public static String getAliSign(AliCommonParam commonRequest, AliPayConfig config) throws PayException {
        // =========== 生成签名开始 ===========
        try {
            Map<String, String> signMap = new HashMap<String, String>();
            // 获取支付宝分配给开发者的应用ID
            signMap.put("app_id", config.getAppId());
            // 业务参数
            signMap.put("biz_content", commonRequest.getBizContent());
            // 编码格式
            signMap.put("charset", Standard.ENCODING_UTF8);
            // 请求方法
            signMap.put("method", commonRequest.getMethod());
            // 通知路径
            signMap.put("notify_url", commonRequest.getNotifyUrl());
            // 加密类型 rsa2
            signMap.put("sign_type", Standard.SIGN_TYPE_RSA2);
            // 请求时间
            signMap.put("timestamp", commonRequest.getTimestamp());
            // 接口版本
            signMap.put("version", "1.0");
            signMap.put("format", Standard.FORMAT_JSON);
            // 获取应用私钥
            String signContent = getSignContent(signMap);
            log.info("排序后的生成签名字段：" + signContent);
            String rsaSign = rsaSign(signContent, config.getAppPrivateKey(), Standard.ENCODING_UTF8, Standard.SIGN_TYPE_RSA2);
            log.info("生成签名：" + rsaSign);
            return rsaSign;
        } catch (Exception e) {
            throw new PayException(Standard.RET_FAIL, e.getMessage());
        }
    }

    public static boolean rsaCheckV1(JSONObject params, String publicKey,
                                     String charset, String signType) throws PayException {
        String sign = params.getString("sign");
        String content = getSignCheckContentV1(params);

        return rsaCheck(content, sign, publicKey, charset, signType);
    }

    public static String getSignCheckContentV1(JSONObject params) {
        if (params == null) {
            return null;
        }
        params.remove("sign");
        params.remove("sign_type");
        StringBuffer content = new StringBuffer();
        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);

        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.getString(key);
            content.append((i == 0 ? "" : "&") + key + "=" + value);
        }
        return content.toString();
    }

    /**
     * 生成待签名报文
     *
     * @param sortedParams
     * @return
     */
    public static String getSignContent(Map<String, String> sortedParams) {
        StringBuffer content = new StringBuffer();
        List<String> keys = new ArrayList(sortedParams.keySet());
        Collections.sort(keys);
        int index = 0;

        for (int i = 0; i < keys.size(); ++i) {
            String key = (String) keys.get(i);
            String value = (String) sortedParams.get(key);
            if (StringUtils.areNotEmpty(new String[]{key, value})) {
                content.append((index == 0 ? "" : "&") + key + "=" + value);
                ++index;
            }
        }
        return content.toString();
    }

    /**
     * 生成签名
     *
     * @param content
     * @param privateKey
     * @param charset
     * @param signType
     * @return
     * @throws PayException
     */
    public static String rsaSign(String content, String privateKey, String charset, String signType) throws PayException {
        if ("RSA".equals(signType)) {
            return rsaSign(content, privateKey, charset);
        } else if ("RSA2".equals(signType)) {
            return rsa256Sign(content, privateKey, charset);
        } else {
            throw new PayException("Sign Type is Not Support : signType=" + signType);
        }
    }

    /**
     * 同步验签
     *
     * @param content
     * @param sign
     * @param publicKey
     * @param charset
     * @param signType
     * @return
     * @throws PayException
     */
    public static boolean rsaCheck(String content, String sign, String publicKey, String charset, String signType) throws PayException {
        if ("RSA".equals(signType)) {
            return rsaCheckContent(content, sign, publicKey, charset);
        } else if ("RSA2".equals(signType)) {
            return rsa256CheckContent(content, sign, publicKey, charset);
        } else {
            throw new PayException("Sign Type is Not Support : signType=" + signType);
        }
    }

    public static boolean rsa256CheckContent(String content, String sign, String publicKey, String charset) throws PayException {
        try {
            PublicKey pubKey = getPublicKeyFromX509("RSA", new ByteArrayInputStream(publicKey.getBytes()));
            Signature signature = Signature.getInstance("SHA256WithRSA");
            signature.initVerify(pubKey);
            if (StringUtils.isEmpty(charset)) {
                signature.update(content.getBytes());
            } else {
                signature.update(content.getBytes(charset));
            }

            return signature.verify(Base64Util.decode(sign));
        } catch (Exception var6) {
            throw new PayException("RSAcontent = " + content + ",sign=" + sign + ",charset = " + charset, var6);
        }
    }

    public static boolean rsaCheckContent(String content, String sign, String publicKey, String charset) throws PayException {
        try {
            PublicKey pubKey = getPublicKeyFromX509("RSA", new ByteArrayInputStream(publicKey.getBytes()));
            Signature signature = Signature.getInstance("SHA1WithRSA");
            signature.initVerify(pubKey);
            if (StringUtils.isEmpty(charset)) {
                signature.update(content.getBytes());
            } else {
                signature.update(content.getBytes(charset));
            }

            return signature.verify(Base64Util.decode(sign));
        } catch (Exception var6) {
            throw new PayException("RSAcontent = " + content + ",sign=" + sign + ",charset = " + charset, var6);
        }
    }


    public static PublicKey getPublicKeyFromX509(String algorithm, InputStream ins) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        StringWriter writer = new StringWriter();
        StreamUtil.io(new InputStreamReader(ins), writer);
        byte[] encodedKey = writer.toString().getBytes();
        encodedKey = Base64Util.decode(new String(encodedKey));
        return keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));
    }


    public static String rsa256Sign(String content, String privateKey, String charset) throws PayException {
        try {
            PrivateKey priKey = getPrivateKeyFromPKCS8("RSA", new ByteArrayInputStream(privateKey.getBytes()));
            Signature signature = Signature.getInstance("SHA256WithRSA");
            signature.initSign(priKey);
            if (StringUtils.isEmpty(charset)) {
                signature.update(content.getBytes());
            } else {
                signature.update(content.getBytes(charset));
            }

            byte[] signed = signature.sign();
//            return new String(Base64Util.encodeBase64(signed));
            return Base64Util.encode(signed);
        } catch (Exception var6) {
            throw new PayException("RSAcontent = " + content + "; charset = " + charset, var6);
        }
    }

    public static String rsaSign(String content, String privateKey, String charset) throws PayException {
        try {
            PrivateKey priKey = getPrivateKeyFromPKCS8("RSA", new ByteArrayInputStream(privateKey.getBytes()));
            Signature signature = Signature.getInstance("SHA1WithRSA");
            signature.initSign(priKey);
            if (StringUtils.isEmpty(charset)) {
                signature.update(content.getBytes());
            } else {
                signature.update(content.getBytes(charset));
            }

            byte[] signed = signature.sign();
            return Base64Util.encode(signed);
        } catch (InvalidKeySpecException var6) {
            throw new PayException("RSA私钥格式不正确，请检查是否正确配置了PKCS8格式的私钥", var6);
        } catch (Exception var7) {
            throw new PayException("RSAcontent = " + content + "; charset = " + charset, var7);
        }
    }

    public static PrivateKey getPrivateKeyFromPKCS8(String algorithm, InputStream ins) throws Exception {
        if (ins != null && !StringUtils.isEmpty(algorithm)) {
            KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
            byte[] encodedKey = StreamUtil.readText(ins).getBytes();
            encodedKey = Base64Util.decode(new String(encodedKey));
            return keyFactory.generatePrivate(new PKCS8EncodedKeySpec(encodedKey));
        } else {
            return null;
        }
    }


}
