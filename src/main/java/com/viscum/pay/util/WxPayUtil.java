package com.viscum.pay.util;

import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

@Slf4j
public class WxPayUtil {
    /**
     * 生成待签名内容
     *
     * @param json
     * @param wxPayKey
     * @return
     */
    public static String getSignContent(JSONObject json, String wxPayKey) {
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
    public static String genDigest(String signContent) {
        return MD5Util.encode(signContent);
    }
}
