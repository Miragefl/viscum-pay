package com.viscum.pay.util;

import com.viscum.pay.base.Standard;
import com.viscum.pay.exception.PayException;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;
import java.util.List;
import java.util.Map;


/**
 * http请求工具类
 *
 * @author fenglei
 */
@Slf4j
public class HttpUtil {

    public static byte[] callPostJSON(String url, JSONObject postData, String contentType, Proxy proxy, TrustManager trustManager) throws PayException {

        String strPost = postData.toString();
        return callPostStr(url, strPost, contentType, proxy, trustManager);
    }

    public static byte[] callPostStr(String url, String postDataStr, String contentType, Proxy proxy, TrustManager trustManager) throws PayException {

        int conTimeout = 30;
        int readTimeout = 30;
        try {
            byte[] res = callAPI(url, Standard.HTTP_POST, postDataStr.getBytes(Standard.ENCODING_UTF8), proxy, trustManager, contentType, conTimeout, readTimeout, Standard.ENCODING_UTF8, null, null);
            return res;
        } catch (UnsupportedEncodingException e) {
            log.info("解析报文出错，字符编码出错", e);
            throw new PayException("H99991", "解析报文出错，字符编码出错", e);
        }
    }

    /**
     * http通信请求基础方法. <br/>
     *
     * @param url             请求的url地址
     * @param method          请求方法 POST GET
     * @param postData        请求body内容
     * @param proxy           代理服务器
     * @param trustManager    ssl请求，请求证书
     * @param contentType     请求type  xml json form
     * @param conTimeout      连接超时时间 单位秒
     * @param readTimeout     读取超时时间 单位秒
     * @param charSet         请求和返回编码 UTF-8 GBK
     * @param returnCookieMap 返回cookie
     * @return
     * @throws PayException 若http通信状态码不是 200 和304 将直接抛出异常
     */
    @SuppressWarnings({"restriction"})
    private static byte[] callAPI(String url, String method, byte[] postData, Proxy proxy, TrustManager trustManager, String contentType, int conTimeout, int readTimeout, String charSet, Map<String, Object> returnCookieMap, Map<String, Object> header)
            throws PayException {
        StringBuilder headContentType = new StringBuilder();
        if (Standard.FORMAT_JSON.equals(contentType) || Standard.FORMAT_XML.equals(contentType)) {
            headContentType.append("application/").append(contentType).append(";charset=").append(charSet);
        }
        //普通表单
        if (Standard.FORMAT_FORM.equals(contentType)) {
            headContentType.append("application/x-www-form-urlencoded;").append("charset=").append(charSet);
        }

        boolean isPost = false;
        if (Standard.HTTP_POST.equals(method)) {
            isPost = true;
        }
        log.info("开始调用接口:" + url);
        // 使用代理
        if (proxy != null) {
            log.info("通过代理建立连接:" + proxy.toString());
        }
        byte[] in2b = new byte[0];
        try {
            java.net.URL reqUrl = null;

            if (url.startsWith("https")) {
                reqUrl = new URL(null, url, new sun.net.www.protocol.https.Handler());
            } else {
                reqUrl = new URL(url);
            }
            HttpURLConnection reqConnection = null;
            // 判断是http请求还是https请求
            if ("https".equals(reqUrl.getProtocol().toLowerCase()) && trustManager != null) {
                SSLContext sslContext = null;
                KeyManager[] keyManage = null;
                try {
                    // 实例化SSL上下文 直接用SSL或者TLS也可以( SSLContext.getInstance("TLS"))
                    sslContext = SSLContext.getInstance("SSL", "SunJSSE");
                    TrustManager[] xtmArray = new TrustManager[]{trustManager};
                    if (trustManager instanceof WxPayX509TrustManager) {
                        keyManage = ((WxPayX509TrustManager) trustManager).getKeyManage();
                    }
                    // 初始化SSL上下文
                    sslContext.init(keyManage, xtmArray, new java.security.SecureRandom());
                } catch (Exception e) {
                    log.info("实例化ssl上下文出现异常", e);
                    throw new PayException("H99994", "实例化ssl上下文出现异常", e);
                }
                // 设置默认的SSLSocketFactory
                if (sslContext != null) {
                    HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
                }
                // 建立URLConnection连接
                try {
                    if (proxy != null) {
                        reqConnection = (HttpsURLConnection) reqUrl.openConnection(proxy);
                    } else {
                        reqConnection = (HttpsURLConnection) reqUrl.openConnection();
                    }
                } catch (Exception e) {
                    log.info("建立https连接异常：", e);
                    throw new PayException("H99992", "建立https连接异常", e);
                }
            } else {
                try {
                    if (proxy != null) {
                        reqConnection = (HttpURLConnection) reqUrl.openConnection(proxy);
                    } else {
                        reqConnection = (HttpURLConnection) reqUrl.openConnection();
                    }
                } catch (Exception e) {
                    log.info("建立https连接异常：", e);
                    throw new PayException("H99993", "与建立http连接异常", e);
                }
            }
            // 设置连接及读取超时间(30秒)
            reqConnection.setConnectTimeout(conTimeout * 1000);
            reqConnection.setReadTimeout(readTimeout * 1000);
            reqConnection.setRequestMethod(method);
            if (header != null && !header.isEmpty()) {
                for (Map.Entry<String, Object> e : header.entrySet()) {
                    reqConnection.setRequestProperty(e.getKey(), String.valueOf(e.getValue()));
                }
            }
            if (!reqConnection.getRequestProperties().containsKey(  "Content-Type")) {
                reqConnection.setRequestProperty("Content-Type", headContentType.toString());
            }
            reqConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.1; Trident/6.0");
            reqConnection.setRequestProperty("DNT", "1");
            reqConnection.setRequestProperty("Connection", "keep-alive");
            reqConnection.setDoInput(true);
            reqConnection.setDoOutput(isPost);
            if (isPost) {
                OutputStream output = null;
                try {
                    output = reqConnection.getOutputStream();
                    output.write(postData);
                } catch (Exception e) {
                    throw e;
                } finally {
                    if (output != null) {
                        output.close();
                    }
                }
            }
            reqConnection.connect();
            int resCode = reqConnection.getResponseCode();
            String resMsg = reqConnection.getResponseMessage();
            // 通讯状态
            log.info("接口接口通信状态码:" + resCode + ", 返回信息:" + resMsg);
            if (200 != resCode && 304 != resCode) {
                throw new PayException("H99995", "通信发生错误" + "httpCode=" + resCode + ";httpMsg=" + resMsg);
            }
            Map<String, List<String>> map = reqConnection.getHeaderFields();
            List<String> returnCookies = map.get("Set-Cookie");
            if (returnCookies != null && returnCookieMap != null) {
                for (int i = 0; i < returnCookies.size(); i++) {
                    String cookieStr = returnCookies.get(i);
                    cookieStr = cookieStr.substring(0, cookieStr.indexOf(";"));
                    int index = cookieStr.indexOf("=");
                    if (index > 0) {
                        returnCookieMap.put(cookieStr.substring(0, index), cookieStr.substring(index + 1));
                    }
                }
            }
            InputStream input = reqConnection.getInputStream();
            ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
            byte[] buff = new byte[1024];
            int rc = 0;
            while ((rc = input.read(buff, 0, 1024)) > 0) {
                swapStream.write(buff, 0, rc);
            }
            in2b = swapStream.toByteArray();
        } catch (PayException e) {
            throw e;
        } catch (Exception e) {
            log.info("调用接口异常：", e);
            throw new PayException("H99999", "通信异常:" + e.getMessage(), e);
        }
        return in2b;
    }
}
