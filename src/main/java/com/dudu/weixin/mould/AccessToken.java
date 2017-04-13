package com.dudu.weixin.mould;

/**
 * 开发者的AccessToken的实体类
 * Created by lizhen on 2017/4/7.
 */

public class AccessToken {
    /**
     * 获取到的凭证
     */
    private String token;
    /**
     * 凭证有效时间，单位：秒
     */
    private int expiresIn;

    /**
     *
     * @return token
     */
    public String getToken() {
        return token;
    }

    /**
     *
     * @param token token
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     *
     * @return expiresIn
     */
    public int getExpiresIn() {
        return expiresIn;
    }

    /**
     *
     * @param expiresIn expiresIn
     */
    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }
}
