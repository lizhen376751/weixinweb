package com.dudu.weixin.mould;

/**
 * Created by Administrator on 2017/3/15.
 */
public class WeixinOauth2Token {
    /**
     *
     */
    private String AccessToken;
    private int ExpiresIn;
    private String RefreshToken;
    private String OpenId;
    private String Scope;
    private String Nickname;
    public String getAccessToken() {
        return AccessToken;
    }
    public void setAccessToken(String accessToken) {
        AccessToken = accessToken;
    }
    public int getExpiresIn() {
        return ExpiresIn;
    }
    public void setExpiresIn(int expiresIn) {
        ExpiresIn = expiresIn;
    }
    public String getRefreshToken() {
        return RefreshToken;
    }
    public void setRefreshToken(String refreshToken) {
        RefreshToken = refreshToken;
    }
    public String getOpenId() {
        return OpenId;
    }
    public void setOpenId(String openId) {
        OpenId = openId;
    }
    public String getScope() {
        return Scope;
    }
    public void setScope(String scope) {
        Scope = scope;
    }

    public String getNickname() {
        return Nickname;
    }
    public void setNickname(String nickname) {
        Nickname = nickname;
    }


}
