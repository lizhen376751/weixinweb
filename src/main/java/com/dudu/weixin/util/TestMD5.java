package com.dudu.weixin.util;


import java.security.MessageDigest;
import java.util.UUID;

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
    public static String[] chars = new String[]{"a", "b", "c", "d", "e", "f",
            "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
            "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N",  "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z"};

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
        /**
         * 以下是生成六位或者八位唯一的编码
         */
        StringBuffer shortBuffer = new StringBuffer();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        for (int i = 0; i < 8; i++) {
            String str = uuid.substring(i * 4, i * 4 + 4);
            int x = Integer.parseInt(str, 16);
            shortBuffer.append(chars[x % 0x3E]);
        }
        System.out.println(shortBuffer.toString());
        String s = new String("123456");
        System.out.println("原始：" + s);
        System.out.println("MD5加密后：" + kL(s));
        System.out.println("解密为MD5后的：" + jM(jM(kL(s))));
    }


}
