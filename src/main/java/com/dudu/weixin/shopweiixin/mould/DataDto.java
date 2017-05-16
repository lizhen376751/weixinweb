package com.dudu.weixin.shopweiixin.mould;

/**
 * Created by Administrator on 2017/5/13.
 */

public class DataDto {
    private String id;
    private String time;
    private String amount;

    public DataDto(String id, String time, String amount) {
        super();
        this.id = id;
        this.time = time;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
