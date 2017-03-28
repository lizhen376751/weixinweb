package com.dudu.weixin.util;

import javax.net.ssl.X509TrustManager;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * 暂时不知道有什么用
 */
public class MyX509TrustManager implements X509TrustManager {
    /**
     *
     * @param chain chain
     * @param authType authType
     * @throws CertificateException CertificateException
     */
    public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
    }

    /**
     *
     * @param chain chain
     * @param authType authType
     * @throws CertificateException CertificateException
     */
    public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
    }

    /**
     *
     * @return null
     */
    public X509Certificate[] getAcceptedIssuers() {
        return null;
    }
}
