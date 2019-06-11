package com.viscum.pay.client;

import com.viscum.pay.config.WxPayConfig;
import com.viscum.pay.exception.PayException;
import com.viscum.pay.model.response.BaseResponse;
import com.viscum.pay.model.request.wxpay.WxRequest;
import com.viscum.pay.util.*;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import javax.net.ssl.TrustManager;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * 微信支付相关接口
 */
@Slf4j
@Service
public class WxPayClient {

    private WxPayConfig wxPayConfig;

    public void setWxPayConfig(WxPayConfig wxPayConfig) {
        this.wxPayConfig = wxPayConfig;
    }

    public <T extends BaseResponse> T execute(WxRequest<T> request) throws PayException {
        TrustManager trustManager = null;
        if (request.needCert()) {
            try {
                trustManager = new WxPayX509TrustManager(wxPayConfig.getKeyPath(), wxPayConfig.getMchId());
            } catch (Exception e) {
                throw new PayException("获取证书出错", e);
            }
        }
        return call(request, request.getResponseClass(), wxPayConfig.getRequestUrl() + request.getMethod(), trustManager);
    }

    /**
     * 验签
     *
     * @param response
     * @return
     */
    public Boolean verifySign(Object response) throws PayException {
        try {
            String jsonStr = JsonParser.modelToJSON(response);
            JSONObject json = JSONObject.fromObject(jsonStr);
            String sign = String.valueOf(json.get("sign"));
            json.remove("sign");
            String signContent = getSignContent(json, wxPayConfig.getAppSecret());
            return genDigest(signContent).equals(sign);
        } catch (IOException e) {
            throw new PayException("实体类转换json出错", e);
        } catch (Exception e) {
            throw new PayException("验签出错", e);
        }
    }

    /**
     * 统一调用微信接口
     *
     * @param request
     * @param responseClazz
     * @param url
     * @return
     * @throws PayException
     */
    private <T> T call(Object request, Class<T> responseClazz, String url, TrustManager trustManager) throws PayException {
        try {
            JSONObject json = JSONObject.fromObject(JsonParser.modelToJSON(request));
            json.put("appid", wxPayConfig.getAppId());
            json.put("mch_id", wxPayConfig.getMchId());
            String signContent = getSignContent(json, wxPayConfig.getMchKey());
            String sign = genDigest(signContent).toUpperCase();
            json.put("sign", sign);
            String params = XmlUtil.parseMapToXml(json).getRootElement().asXML();
            log.info("上送微信接口报文：" + params);
            String xml = HttpUtil.callPostStr(url, params, "form", null, trustManager);
            T t = XmlUtil.xmlStringToModel(xml, responseClazz, true);
            log.info("微信接口返回：" + t.toString());
            return t;
        } catch (IOException e) {
            throw new PayException("request to json error", e);
        } catch (JAXBException e) {
            throw new PayException("request to xml error", e);
        } catch (SAXException e) {
            throw new PayException("request to xml error", e);
        } catch (ParserConfigurationException e) {
            throw new PayException("request to xml error", e);
        } catch (PayException e) {
            throw e;
        } catch (Exception e) {
            throw new PayException("request to xml error", e);
        }
    }

    /**
     * 生成待签名内容
     *
     * @param json
     * @param wxPayKey
     * @return
     */
    public String getSignContent(JSONObject json, String wxPayKey) {
        List<String> list = new ArrayList<String>();
        String strTmp = "";
        Iterator it = json.keySet().iterator();
        String key = "";
        while (it.hasNext()) {
            key = (String) it.next();
            list.add(key);
        }
        Collections.sort(list);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append("&" + list.get(i) + "=" + json.getString(list.get(i)));
        }
        sb.append("&key=" + wxPayKey);
        strTmp = sb.substring(1);
        log.debug("传入的报文字段排序后()：->" + strTmp);
        return strTmp;
    }

    /**
     * 生成数据摘要
     *
     * @param signContent
     * @return
     */
    public String genDigest(String signContent) {
        return MD5Util.encode(signContent);
    }

}
