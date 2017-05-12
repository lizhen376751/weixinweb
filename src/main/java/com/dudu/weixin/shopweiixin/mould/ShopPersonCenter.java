package com.dudu.weixin.shopweiixin.mould;

/**
 * 店管家微信的个人中心实体类
 * Created by lizhen on 2017/5/12.
 */

public class ShopPersonCenter {
    /**
     * 客户唯一表示ID
     */
    private Integer id;
    /**
     * 车牌号码
     */
    private String plateNumber;
    /**
     * 车辆品牌
     */
    private String carBrand;
    /**
     * 车系
     */
    private String carSeries;
    /**
     * 车辆型号
     */
    private String carModel;
    /**
     * 当前里程
     */
    private String currentmileage;
    /**
     * 保养提醒
     */
    private String maintenanceReminder;

    /**
     * ahi总分
     */
    private Integer point;

    /**
     *  ShopPersonCenter(店管家微信的个人中心实体类) 字符串形式
     * @return ShopPersonCenter(店管家微信的个人中心实体类)字符串
     */
    @Override
    public String toString() {
        return "id:" + id + ",plateNumber:" + plateNumber + ",carBrand:" + carBrand + ",carSeries:" + carSeries + ",carModel:" + carModel + ",currentmileage:" + currentmileage
                + ",maintenanceReminder:" + maintenanceReminder + ",point:" + point;
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
     * @return 返回 ShopPersonCenter(店管家微信的个人中心实体类)
     */
    public ShopPersonCenter setId(Integer id) {
        this.id = id;
        return this;
    }

    /**
     * 获取 车牌号码
     * @return plateNumber 车牌号码
     */
    public String getPlateNumber() {
        return this.plateNumber;
    }

    /**
     * 设置 车牌号码
     * @param plateNumber 车牌号码
     * @return 返回 ShopPersonCenter(店管家微信的个人中心实体类)
     */
    public ShopPersonCenter setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
        return this;
    }

    /**
     * 获取 车辆品牌
     * @return carBrand 车辆品牌
     */
    public String getCarBrand() {
        return this.carBrand;
    }

    /**
     * 设置 车辆品牌
     * @param carBrand 车辆品牌
     * @return 返回 ShopPersonCenter(店管家微信的个人中心实体类)
     */
    public ShopPersonCenter setCarBrand(String carBrand) {
        this.carBrand = carBrand;
        return this;
    }

    /**
     * 获取 车系
     * @return carSeries 车系
     */
    public String getCarSeries() {
        return this.carSeries;
    }

    /**
     * 设置 车系
     * @param carSeries 车系
     * @return 返回 ShopPersonCenter(店管家微信的个人中心实体类)
     */
    public ShopPersonCenter setCarSeries(String carSeries) {
        this.carSeries = carSeries;
        return this;
    }

    /**
     * 获取 车辆型号
     * @return carModel 车辆型号
     */
    public String getCarModel() {
        return this.carModel;
    }

    /**
     * 设置 车辆型号
     * @param carModel 车辆型号
     * @return 返回 ShopPersonCenter(店管家微信的个人中心实体类)
     */
    public ShopPersonCenter setCarModel(String carModel) {
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
     * @return 返回 ShopPersonCenter(店管家微信的个人中心实体类)
     */
    public ShopPersonCenter setCurrentmileage(String currentmileage) {
        this.currentmileage = currentmileage;
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
     * @return 返回 ShopPersonCenter(店管家微信的个人中心实体类)
     */
    public ShopPersonCenter setMaintenanceReminder(String maintenanceReminder) {
        this.maintenanceReminder = maintenanceReminder;
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
     * @return 返回 ShopPersonCenter(店管家微信的个人中心实体类)
     */
    public ShopPersonCenter setPoint(Integer point) {
        this.point = point;
        return this;
    }
}
