/*
 * RsaUtil.java Copyright BrightStars Tech Co. Ltd. All Rights Reserved.
 */
package com.viscum.pay.util;

import com.viscum.pay.enums.SignType;
import com.viscum.pay.exception.PayException;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * <p>
 *
 * <p>
 *
 * @author fenglei
 * @since 2019-06-10
 */
public class RsaUtil {
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
        if (SignType.RSA1.toString().equals(signType)) {
            return rsaCheckContent(content, sign, publicKey, charset);
        } else if (SignType.RSA2.toString().equals(signType)) {
            return rsa256CheckContent(content, sign, publicKey, charset);
        } else {
            throw new PayException("Sign Type is Not Support : signType=" + signType);
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
        if (SignType.RSA1.toString().equals(signType)) {
            return rsaSign(content, privateKey, charset);
        } else if (SignType.RSA2.toString().equals(signType)) {
            return rsa256Sign(content, privateKey, charset);
        } else {
            throw new PayException("Sign Type is Not Support : signType=" + signType);
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
            return Base64Util.encode(signed);
        } catch (Exception var6) {
            throw new PayException("RSAcontent = " + content + "; charset = " + charset, var6);
        }
    }

    /**
     * 获取公钥
     *
     * @param algorithm
     * @param ins
     * @return
     * @throws Exception
     */
    public static PublicKey getPublicKeyFromX509(String algorithm, InputStream ins) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        StringWriter writer = new StringWriter();
        StreamUtil.io(new InputStreamReader(ins), writer);
        byte[] encodedKey = writer.toString().getBytes();
        encodedKey = Base64Util.decode(new String(encodedKey));
        return keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));
    }

    /**
     * 获取私钥
     *
     * @param algorithm
     * @param ins
     * @return
     * @throws Exception
     */
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
