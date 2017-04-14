package com.dudu.weixin.mould;


/**
 * 菜单的一级按钮
 */
public class ComplexButton extends Button {
    /**
     * 二级按钮
     */
    private Button[] sub_button;

    /**
     * @return sub_button
     */
    public Button[] getSub_button() {
        return sub_button;
    }

    /**
     * @param sub_button subbutton
     */
    public void setSubbutton(Button[] sub_button) {
        this.sub_button = sub_button;
    }
}