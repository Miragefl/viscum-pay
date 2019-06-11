package com.viscum.pay.util;


import javax.net.ssl.*;
import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;


/**
 * 自定义一个TrustManager方法，实现X509TrustManager接口，用来加载证书库，并当前证书是否被信任
 *
 * @author duys
 * @version 2013-08-03
 */
public class WxPayX509TrustManager implements X509TrustManager {

    /**
     * X.509类型的TrustManager
     */
    private X509TrustManager sunJSSEX509TrustManager;
    private KeyManager[] keymanagers;

    public WxPayX509TrustManager(String keystorePath, String wxPayCertKey) throws Exception {

        this.keymanagers = new KeyManager[]{};
        // 获取秘钥库
        KeyStore ks = this.getKeyStorePKCS12(keystorePath, wxPayCertKey);
        initKeyManage(ks, wxPayCertKey);

        // 实例化信任库
        TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509", "SunJSSE");
//        // 初始化信任库
        tmf.init(ks);
        TrustManager[] tms = tmf.getTrustManagers();
        /*
         * 遍历TrustManager,如果找到X509TrustManage则直接返回，并作为默认的trust manager,否则抛出异常信息
         */
        for (int i = 0; i < tms.length; i++) {
            if (tms[i] instanceof X509TrustManager) {
                sunJSSEX509TrustManager = (X509TrustManager) tms[i];
                return;
            }
        }

    }

    /**
     * 获得KeyStore.
     *
     * @param keyStorePath 密钥库路径
     * @param password     密码
     * @return 密钥库
     * @throws Exception
     */
    public KeyStore getKeyStore(String keyStorePath, String password) throws Exception {
        FileInputStream keystoreInputStream = null;
        KeyStore ks = null;
        try {
            // 实例化密钥库
            ks = KeyStore.getInstance("JKS");
            // 获得密钥库文件流
            keystoreInputStream = new FileInputStream(keyStorePath);
            // 加载密钥库
            ks.load(keystoreInputStream, password.toCharArray());
        } catch (Exception e) {
            throw e;
        } finally {
            // 关闭密钥库文件流
            if (keystoreInputStream != null) {
                keystoreInputStream.close();
            }
        }
        return ks;
    }

    /**
     * 加载PKCS12
     *
     * @param keyStorePath
     * @param password
     * @return
     * @throws Exception
     */
    public KeyStore getKeyStorePKCS12(String keyStorePath, String password) throws Exception {
        FileInputStream keystoreInputStream = null;
        KeyStore ks = null;
        try {
            // 实例化密钥库  
            ks = KeyStore.getInstance("PKCS12");
            // 获得密钥库文件流  
            keystoreInputStream = new FileInputStream(keyStorePath);
            // 加载密钥库  
            ks.load(keystoreInputStream, password.toCharArray());
        } catch (Exception e) {
            throw e;
        } finally {
            // 关闭密钥库文件流  
            if (keystoreInputStream != null) {
                keystoreInputStream.close();
            }
        }
        return ks;
    }

    /**
     * @param keystore
     * @param password
     * @return
     * @throws Exception
     */
    private void initKeyManage(KeyStore keystore, String password) throws Exception {

        KeyManagerFactory kmfactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        char[] keyPassword = password.toCharArray();
        kmfactory.init(keystore, keyPassword);
        KeyManager[] kms = kmfactory.getKeyManagers();
        if (kms != null) {
            this.keymanagers = kms;
        }
    }


    public KeyManager[] getKeyManage() {
        return this.keymanagers;
    }


    /**
     * 校验客户端证书是否被信任
     */
    @Override
    public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
//        try {
//            sunJSSEX509TrustManager.checkClientTrusted(chain, authType);
//        } catch (CertificateException excep) {
//        	throw excep;
//        }
    }

    /**
     * 校验服务器端证书是否被信任
     */
    @Override
    public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
//        try {
//            sunJSSEX509TrustManager.checkServerTrusted(chain, authType);
//        } catch (CertificateException excep) {
//        	throw excep;
//        }
    }

    /**
     * 返回接受的发行商数组
     */
    @Override
    public X509Certificate[] getAcceptedIssuers() {
        return sunJSSEX509TrustManager.getAcceptedIssuers();
    }

}
