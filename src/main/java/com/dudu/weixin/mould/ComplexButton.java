package com.dudu.weixin.mould;


/**
 * 菜单按钮
 * 二级菜单
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