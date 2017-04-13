package com.dudu.weixin.mould;

/**
 * Created by Administrator on 2017/3/15.
 */
public class WeixinOauth2Token {
    /**
     * 网页授权的accessToken
     */
    private String accessToken;
    /**
     * 有效时间
     */
    private int expiresIn;
    /**
     * 刷新的
     */
    private String refreshToken;
    /**
     * 客户id
     */
    private String openId;
    /**
     * 作用域
     */
    private String scope;
    /**
     * 不知道
     */
    private String nickname;

    /**
     * @return accessToken
     */
    public String getAccessToken() {
        return accessToken;
    }

    /**
     * @param accessToken accessToken
     */
    public void setAccessToken(String accessToken) {
        accessToken = accessToken;
    }

    /**
     * @return expiresIn
     */
    public int getExpiresIn() {
        return expiresIn;
    }

    /**
     * @param expiresIn expiresIn
     */
    public void setExpiresIn(int expiresIn) {
        expiresIn = expiresIn;
    }

    /**
     * @return refreshToken
     */
    public String getRefreshToken() {
        return refreshToken;
    }

    /**
     * @param refreshToken refreshToken
     */
    public void setRefreshToken(String refreshToken) {
        refreshToken = refreshToken;
    }

    /**
     * @return openId
     */
    public String getOpenId() {
        return openId;
    }

    /**
     * @param openId openId
     */
    public void setOpenId(String openId) {
        openId = openId;
    }

    /**
     * @return scope
     */
    public String getScope() {
        return scope;
    }

    /**
     * @param scope scope
     */
    public void setScope(String scope) {
        scope = scope;
    }

    /**
     * @return nickname
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * @param nickname nickname
     */
    public void setNickname(String nickname) {
        nickname = nickname;
    }


}
