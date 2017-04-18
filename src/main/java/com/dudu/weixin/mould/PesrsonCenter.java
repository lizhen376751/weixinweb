package com.dudu.weixin.mould;

import com.dudu.soa.lmk.operate.module.LianmengkaXmLeftResultModule;

import java.io.Serializable;
import java.util.List;

/**
 * 个人中心实体类
 * Created by lizhen on 2017/4/18.
 */

public class PesrsonCenter implements Serializable {
    /**
     * 客户唯一表示ID
     */
    private Integer id;
    /**
     * 车牌号码
     */
    private String carHaopai;
    /**
     * 车辆品牌
     */
    private Integer carBrand;
    /**
     * 车系
     */
    private Integer carSeries;
    /**
     * 车辆型号
     */
    private Integer carModel;
    /**
     * 当前里程
     */
    private String currentmileage;
    /**
     * 联盟卡列表
     */
    private  List<LianmengkaXmLeftResultModule> lianmengkaXmLeftResultModules;
    /**
     * ahi总分
     */
    private Integer point;
    /**
     * 保养提醒
     */
    private String maintenanceReminder;


    /**
     *  PesrsonCenter(个人中心实体类) 字符串形式
     * @return PesrsonCenter(个人中心实体类)字符串
     */
    @Override
    public String toString() {
        return "id:" + id + ",carHaopai:" + carHaopai + ",carBrand:" + carBrand + ",carSeries:" + carSeries + ",carModel:" + carModel + ",currentmileage:" + currentmileage
                + ",lianmengkaXmLeftResultModules:" + lianmengkaXmLeftResultModules + ",point:" + point + ",maintenanceReminder:" + maintenanceReminder;
    }

    /**
     * 获取 客户唯一表示ID
     * @return id 客户唯一表示ID
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * 设置 客户唯一表示ID
     * @param id 客户唯一表示ID
     * @return 返回 PesrsonCenter(个人中心实体类)
     */
    public PesrsonCenter setId(Integer id) {
        this.id = id;
        return this;
    }

    /**
     * 获取 车牌号码
     * @return carHaopai 车牌号码
     */
    public String getCarHaopai() {
        return this.carHaopai;
    }

    /**
     * 设置 车牌号码
     * @param carHaopai 车牌号码
     * @return 返回 PesrsonCenter(个人中心实体类)
     */
    public PesrsonCenter setCarHaopai(String carHaopai) {
        this.carHaopai = carHaopai;
        return this;
    }

    /**
     * 获取 车辆品牌
     * @return carBrand 车辆品牌
     */
    public Integer getCarBrand() {
        return this.carBrand;
    }

    /**
     * 设置 车辆品牌
     * @param carBrand 车辆品牌
     * @return 返回 PesrsonCenter(个人中心实体类)
     */
    public PesrsonCenter setCarBrand(Integer carBrand) {
        this.carBrand = carBrand;
        return this;
    }

    /**
     * 获取 车系
     * @return carSeries 车系
     */
    public Integer getCarSeries() {
        return this.carSeries;
    }

    /**
     * 设置 车系
     * @param carSeries 车系
     * @return 返回 PesrsonCenter(个人中心实体类)
     */
    public PesrsonCenter setCarSeries(Integer carSeries) {
        this.carSeries = carSeries;
        return this;
    }

    /**
     * 获取 车辆型号
     * @return carModel 车辆型号
     */
    public Integer getCarModel() {
        return this.carModel;
    }

    /**
     * 设置 车辆型号
     * @param carModel 车辆型号
     * @return 返回 PesrsonCenter(个人中心实体类)
     */
    public PesrsonCenter setCarModel(Integer carModel) {
        this.carModel = carModel;
        return this;
    }

    /**
     * 获取 当前里程
     * @return currentmileage 当前里程
     */
    public String getCurrentmileage() {
        return this.currentmileage;
    }

    /**
     * 设置 当前里程
     * @param currentmileage 当前里程
     * @return 返回 PesrsonCenter(个人中心实体类)
     */
    public PesrsonCenter setCurrentmileage(String currentmileage) {
        this.currentmileage = currentmileage;
        return this;
    }

    /**
     * 获取 联盟卡列表
     * @return lianmengkaXmLeftResultModules 联盟卡列表
     */
    public List<LianmengkaXmLeftResultModule> getLianmengkaXmLeftResultModules() {
        return this.lianmengkaXmLeftResultModules;
    }

    /**
     * 设置 联盟卡列表
     * @param lianmengkaXmLeftResultModules 联盟卡列表
     * @return 返回 PesrsonCenter(个人中心实体类)
     */
    public PesrsonCenter setLianmengkaXmLeftResultModules(List<LianmengkaXmLeftResultModule> lianmengkaXmLeftResultModules) {
        this.lianmengkaXmLeftResultModules = lianmengkaXmLeftResultModules;
        return this;
    }

    /**
     * 获取 ahi总分
     * @return point ahi总分
     */
    public Integer getPoint() {
        return this.point;
    }

    /**
     * 设置 ahi总分
     * @param point ahi总分
     * @return 返回 PesrsonCenter(个人中心实体类)
     */
    public PesrsonCenter setPoint(Integer point) {
        this.point = point;
        return this;
    }

    /**
     * 获取 保养提醒
     * @return maintenanceReminder 保养提醒
     */
    public String getMaintenanceReminder() {
        return this.maintenanceReminder;
    }

    /**
     * 设置 保养提醒
     * @param maintenanceReminder 保养提醒
     * @return 返回 PesrsonCenter(个人中心实体类)
     */
    public PesrsonCenter setMaintenanceReminder(String maintenanceReminder) {
        this.maintenanceReminder = maintenanceReminder;
        return this;
    }
}
