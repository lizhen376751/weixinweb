package com.dudu.weixin.service;

import com.dudu.weixin.mould.ceshi;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * 原BaoYangTiXing
 */
@Service
public class BaoYangTiXingService {
	
	
	
	/**
	 * 根据联盟总部编码和车牌号获取车辆保养提醒信息（客户需求信息）
	 * @param lmCode
	 * @param carNo
	 * @param top
	 * @return
	 * @throws Exception
	 */
	public ArrayList getBaoYangListByLmcodeAndCarNo(String lmCode,String carNo,String top)  {
		ArrayList list = new ArrayList();
		int num=Integer.parseInt(top)*10;
		ceshi ceshi1 = new ceshi();
		ceshi1.setCustomerDemand_DateBegin("2017-3-30");
		ceshi1.setCustomerDemand_Memo("更换刹车片");
		ceshi1.setModel_name1("汽车养护");
		ceshi1.setModel_name2("空气滤芯");
		ceshi1.setCustomerDemand_Level("1");
		ceshi1.setShopName("北京经典");
		list.add(ceshi1);
		//TODO 后期封装成接口
		System.out.println("保养提醒进入");
		return list;
	}
	
	
	
}
