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
     * 生成指定长度的随机码
     *
     * @param len
     * @return
     */
    public static String getRandomChar(int len) {
        Random random = new Random();
        char ch = '0';
        LinkedList<String> ls = new LinkedList<String>();
        // 0-9
        for (int i = 0; i < 10; i++) {
            ls.add(String.valueOf(48 + i));
        }
        // A-Z
        for (int i = 0; i < 26; i++) {
            ls.add(String.valueOf(65 + i));
        }
        // a-z
        for (int i = 0; i < 26; i++) {
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

}
