package com.viscum.pay.util;

import lombok.extern.slf4j.Slf4j;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Slf4j
public class MD5Util {
    // 全局数组
    private final static String[] strDigits = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};


    public static String genDigest(String digestStr) throws NoSuchAlgorithmException {
        MessageDigest md;
        String pwd = "";
        // 生成一个sha1加密计算摘要
        md = MessageDigest.getInstance("sha1"); // 同样可以使用SHA1
        // 计算sha1函数
        md.update(digestStr.getBytes());
        // digest()最后确定返回sha1 hash值，返回值为8为字符串。因为sha1
        // hash值是16位的hex值，实际上就是8位的字符
        // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
        // pwd = new BigInteger(1, md.digest()).toString(16);
        // //参数也可不只用16可改动,当然结果也不一样了
        pwd = byte2Hex(md.digest());
        log.debug("传入的报文字段排序后md5(tangwei)：->" + pwd);
        return pwd;
    }

    /**
     * 字节数组转换16进制
     *
     * @param b
     * @return
     */
    private static String byte2Hex(byte[] b) {
        StringBuilder sb = new StringBuilder();
        String tmp = null;
        for (int i = 0; i < b.length; i++) {
            tmp = Integer.toHexString(b[i] & 0xff);
            if (tmp.length() == 1) {
                sb.append("0");
            }
            sb.append(tmp);
        }
        return sb.toString();
    }

    public static String encode(String strObj) {
        String resultString = null;
        try {
            resultString = new String(strObj);
            MessageDigest md = MessageDigest.getInstance("MD5");
            // md.digest() 该函数返回值为存放哈希值结果的byte数组
            resultString = byteToString(md.digest(strObj.getBytes()));
        } catch (NoSuchAlgorithmException ex) {
            // ex.printStackTrace();
            log.error("", ex);
        }
        return resultString;
    }

    // 转换字节数组为16进制字串
    private static String byteToString(byte[] bByte) {
        StringBuffer sBuffer = new StringBuffer();
        for (int i = 0; i < bByte.length; i++) {
            sBuffer.append(byteToArrayString(bByte[i]));
        }
        return sBuffer.toString();
    }

    // 返回形式为数字跟字符串
    private static String byteToArrayString(byte bByte) {
        int iRet = bByte;
        // System.out.println("iRet="+iRet);
        if (iRet < 0) {
            iRet += 256;
        }
        int iD1 = iRet / 16;
        int iD2 = iRet % 16;
        return strDigits[iD1] + strDigits[iD2];
    }
}
