package com.dudu.weixin.util;


import java.security.MessageDigest;

/**
 * 密码加密
 */

public final class TestMD5 {
    /**
     * 常量
     */
    private static final int NUM = 16;
    /**
     * 常量
     */
    private static final int NUM2 = 0xff;

    private TestMD5() {
    }

    /**
     * 可逆的加密算法
     *
     * @param inStr 字符串
     * @return ss
     */
    public static String kL(String inStr) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }
        char[] charArray = inStr.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++) {
            byteArray[i] = (byte) charArray[i];
        }
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & NUM2;
            if (val < NUM) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

    /**
     * 加密后解密
     *
     * @param inStr 字符串
     * @return dd
     */
    public static String jM(String inStr) {
        char[] a = inStr.toCharArray();
        for (int i = 0; i < a.length; i++) {
            a[i] = (char) (a[i] ^ 't');
        }
        String k = new String(a);
        return k;
    }

    /**
     * @param args 主方法
     */
    public static void main(String[] args) {

        String s = new String("123456");
        System.out.println("原始：" + s);
        System.out.println("MD5加密后：" + kL(s));
        System.out.println("解密为MD5后的：" + jM(jM(kL(s))));
    }
}
