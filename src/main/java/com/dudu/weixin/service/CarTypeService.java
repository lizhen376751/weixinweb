package com.dudu.weixin.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dudu.soa.customercenter.customer.api.ApiCusCar;
import com.dudu.soa.customercenter.customer.module.Car;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 车辆的品牌车型车系
 * Created by lizhen on 2017/4/18.
 */
@Service
public class CarTypeService {
    /**
     * 引入查询车辆车型品牌和型号
     */
    @Reference(version = "0.0.1")
    private ApiCusCar apiCusCar;

    /**
     * 查询车辆品牌车系的总方法
     *
     * @param type 查询什么?类型
     * @param num  品牌或者车系的id
     * @return
     */
    public List<Car> queryAllCar(String type, Integer num) {
        switch (type) {
            //查询车辆品牌
            case "CarBrand":
                return apiCusCar.queryCarBrand();
            //根据品牌查询车系
            case "CarSeries":
                return apiCusCar.queryCarSeries(num);
            //根据车系查询车辆型号
            case "CarModel":
                return apiCusCar.queryCarModel(num);
        }
        return null;
    }

    /**
     * 查询车辆品牌
     *
     * @return 车辆品牌
     */
    public List<Car> queryCarBrand() {
        return apiCusCar.queryCarBrand();
    }

    /**
     * 根据品牌的id查询品牌名称
     *
     * @param carBrand 品牌的id
     * @return 中文的品牌名称
     */
    public String getCarBrandName(Integer carBrand) {
        List<Car> carBrandList = this.queryCarBrand();
        return this.getName(carBrandList, carBrand);
    }


    /**
     * 查询车系
     *
     * @param num 根据品牌查询车系
     * @return 车辆集合
     */
    public List<Car> queryCarSeries(Integer num) {
        List<Car> cars = apiCusCar.queryCarSeries(num);
        return cars;
    }

    /**
     * 根据车系id查询车系名称
     *
     * @param carBrand  车辆品牌
     * @param carSeries 车系
     * @return 车系名称
     */
    public String getCarSeriesName(Integer carBrand, Integer carSeries) {
        List<Car> cars = this.queryCarSeries(carBrand);
        return this.getName(cars, carSeries);
    }

    /**
     * 查询车辆型号
     *
     * @param num 车辆类别
     * @return 车辆
     */
    public List<Car> queryCarModel(Integer num) {
        List<Car> cars = apiCusCar.queryCarModel(num);
        return cars;
    }

    /**
     * 根据车系，查询对应的中文名称
     *
     * @param carSeries 车型
     * @param carModel  车系id
     * @return 车型名称
     */
    public String getCarModelName(Integer carSeries, Integer carModel) {
        List<Car> cars = this.queryCarModel(carSeries);
        String name = this.getName(cars, carModel);
        return name;
    }

    /**
     * 查询中文名字
     *
     * @param cars 列表集合
     * @param num  传入要查找的id
     * @return 中文名字
     */
    public String getName(List<Car> cars, Integer num) {
        if (cars != null && cars.size() > 0) {
            for (Car car : cars) {
                int carId = car.getCarId();
                if (num == carId) {
                    return car.getCarName();
                }
            }
        }
        return null;
    }
}
