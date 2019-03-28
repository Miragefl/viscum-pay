package com.viscum.pay.util;

import com.viscum.pay.exception.PayException;
import com.viscum.pay.base.Standard;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 此类提供�?��基本的帮助功能，如想要更加便捷的、更加丰富的帮助功能可以在SystempHelper类中扩充�?
 *
 * @author zhaojianzhong
 */
@Slf4j
public class Helper {


    public static String getCurrentTime(String format) {
        java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(format);
        Date now = new Date();
        return formatter.format(now);
    }

    /**
     * 校验入参是否为null||""
     *
     * @param params
     * @param args
     * @return
     * @throws PayException
     */
    public static JSONObject checkNull(Map<String, Object> params, String[] args) throws PayException {
        for (String t : args) {
            if (!params.containsKey(t)) {
                throw new PayException(Standard.RET_FAIL, "参数[" + t + "]为必须字段!");
            } else {
                if (StringUtils.isBlank(String.valueOf(params.get(t))) || "null".equals(String.valueOf(params.get(t)))) {
                    throw new PayException(Standard.RET_FAIL, "参数[" + t + "]不能为空!");
                }
            }
        }
        return null;
    }


    /**
     * 生成指定长度的随机码
     *
     * @param len
     * @return
     */
    public static String getRandomChar(int len) {
        Random random = new Random();
        char ch = '0';
        LinkedList<String> ls = new LinkedList<String>();
        for (int i = 0; i < 10; i++) {// 0-9
            ls.add(String.valueOf(48 + i));
        }
        for (int i = 0; i < 26; i++) {// A-Z
            ls.add(String.valueOf(65 + i));
        }
        for (int i = 0; i < 26; i++) {// a-z
            ls.add(String.valueOf(97 + i));
        }
        StringBuilder sb = new StringBuilder();
        int index;
        for (int i = 0; i < len; i++) {
            index = random.nextInt(ls.size());
            if (index > (ls.size() - 1)) {
                index = ls.size() - 1;
            }
            ch = (char) Integer.parseInt(String.valueOf(ls.get(index)));
            sb.append(ch);
        }
        return sb.toString();
    }


    public static String getTimestampStr() {
        return String.valueOf(System.currentTimeMillis());
    }

    public static String getClientIp(HttpServletRequest request) {
        String ip = "127.0.0.1";
        try {
            ip = request.getHeader("x-forwarded-for");
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }

            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }
            if (ip.indexOf(",") > 0) {
                ip = ip.split(",")[0];
            }
            return ip;
        } catch (Exception e) {
            return ip;
        }

    }
}
