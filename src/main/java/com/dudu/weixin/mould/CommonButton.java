package com.dudu.weixin.mould;


/**
 *菜单类
 * 二级按钮
 */
public class CommonButton extends Button {
    /**
     *类型
     */
    private String type;
    /**
     *按钮
     */
    private String key;
    /**
     *路径
     */
    private String url;

    /**
     * @return url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return key
     */
    public String getKey() {
        return key;
    }

    /**
     * @param key key
     */
    public void setKey(String key) {
        this.key = key;
    }
}