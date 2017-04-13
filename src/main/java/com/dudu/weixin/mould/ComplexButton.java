package com.dudu.weixin.mould;


/**
 * 菜单的一级按钮
 *
 */
public class ComplexButton extends Button {
    /**
     * 二级按钮
     */
    private Button[] subbutton;

    /**
     * @return subbutton
     */
    public Button[] getSubbutton() {
        return subbutton;
    }

    /**
     * @param subbutton subbutton
     */
    public void setSubbutton(Button[] subbutton) {
        this.subbutton = subbutton;
    }
}