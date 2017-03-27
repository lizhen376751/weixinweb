package com.dudu.weixin.mould;

/**
 * Created by Administrator on 2017/3/22.
 * 测试制造数据使用(保养提醒)
 */
public class ceshi {
    public String CustomerDemand_DateBegin;
    public String shopName;
    public String CustomerDemand_Memo;
    public String model_name1;
    public String model_name2;
    public String CustomerDemand_Level;

    @Override
    public String toString() {
        return "ceshi{" +
                "CustomerDemand_DateBegin='" + CustomerDemand_DateBegin + '\'' +
                ", shopName='" + shopName + '\'' +
                ", CustomerDemand_Memo='" + CustomerDemand_Memo + '\'' +
                ", model_name1='" + model_name1 + '\'' +
                ", model_name2='" + model_name2 + '\'' +
                ", CustomerDemand_Level='" + CustomerDemand_Level + '\'' +
                '}';
    }

    public String getCustomerDemand_DateBegin() {
        return CustomerDemand_DateBegin;
    }

    public void setCustomerDemand_DateBegin(String customerDemand_DateBegin) {
        CustomerDemand_DateBegin = customerDemand_DateBegin;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getCustomerDemand_Memo() {
        return CustomerDemand_Memo;
    }

    public void setCustomerDemand_Memo(String customerDemand_Memo) {
        CustomerDemand_Memo = customerDemand_Memo;
    }

    public String getModel_name1() {
        return model_name1;
    }

    public void setModel_name1(String model_name1) {
        this.model_name1 = model_name1;
    }

    public String getModel_name2() {
        return model_name2;
    }

    public void setModel_name2(String model_name2) {
        this.model_name2 = model_name2;
    }

    public String getCustomerDemand_Level() {
        return CustomerDemand_Level;
    }

    public void setCustomerDemand_Level(String customerDemand_Level) {
        CustomerDemand_Level = customerDemand_Level;
    }
}
