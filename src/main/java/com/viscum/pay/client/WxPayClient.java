package com.viscum.pay.client;

import com.viscum.pay.base.Standard;
import com.viscum.pay.config.WxPayConfig;
import com.viscum.pay.exception.PayException;
import com.viscum.pay.model.response.BaseResponse;
import com.viscum.pay.model.request.wxpay.WxRequest;
import com.viscum.pay.util.*;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.dom4j.Document;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import javax.net.ssl.TrustManager;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.*;

/**
 * 微信客户端
 *
 * @author fenglei
 */
@Component
public class WxPayClient {

    private Logger logger = LoggerFactory.getLogger(WxPayClient.class);

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
            // 生成请求参数
            String params = generateRequestParam(request);
            // 请求微信接口
            byte[] bytes = HttpUtil.callPostStr(url, params, "form", null, trustManager);
            // 解析返回报文
            SAXReader saxReader = new SAXReader(false);
            ByteArrayInputStream in = new ByteArrayInputStream(bytes);
            Document document = saxReader.read(in);
            JSONObject responseJson = JSONObject.fromObject(XmlUtil.parseXmlToMap(document));
            logger.info("微信返回参数：{}", responseJson);
            // 验签
            if (verifySign(responseJson)) {
                return XmlUtil.xmlStringToModel(new String(bytes, Standard.ENCODING_UTF8), responseClazz, true);
            } else {
                throw new PayException("签名出错");
            }
        } catch (IOException e) {
            throw new PayException("request to json error", e);
        } catch (JAXBException | ParserConfigurationException | SAXException e) {
            throw new PayException("request to xml error", e);
        } catch (PayException e) {
            throw e;
        } catch (Exception e) {
            throw new PayException("request to xml error", e);
        }
    }

    /**
     * 生成请求参数
     *
     * @param request
     * @return
     * @throws IOException
     */
    public String generateRequestParam(Object request) throws IOException {
        JSONObject json = JSONObject.fromObject(JsonParser.modelToJSON(request));
        json.put("appid", wxPayConfig.getAppId());
        json.put("mch_id", wxPayConfig.getMchId());
        String signContent = getSignContent(json, wxPayConfig.getMchKey());
        String sign = genDigest(signContent).toUpperCase();
        json.put("sign", sign);
        String params = XmlUtil.parseMapToXml(json).getRootElement().asXML();
        logger.info("上送微信接口报文：" + params);
        return params;
    }


    /**
     * 生成待签名内容
     *
     * @param json
     * @param wxPayKey
     * @return
     */
    public String getSignContent(JSONObject json, String wxPayKey) {
        List<String> list = new ArrayList<>();
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
            sb.append("&").append(list.get(i)).append("=").append(json.getString(list.get(i)));
        }
        sb.append("&key=").append(wxPayKey);
        strTmp = sb.substring(1);
        logger.debug("传入的报文字段排序后()：->" + strTmp);
        return strTmp;
    }

    /**
     * 生成数据摘要
     *
     * @param signContent
     * @return
     */
    public String genDigest(String signContent) {
        return Md5Util.encode(signContent);
    }

    /**
     * 验签
     *
     * @param json
     * @return
     */
    public Boolean verifySign(JSONObject json) throws PayException {
        try {
            String sign = String.valueOf(json.get("sign"));
            json.remove("sign");
            String signContent = getSignContent(json, wxPayConfig.getMchKey());
            return genDigest(signContent).toUpperCase().equals(sign);
        } catch (Exception e) {
            throw new PayException("验签出错", e);
        }
    }

}
