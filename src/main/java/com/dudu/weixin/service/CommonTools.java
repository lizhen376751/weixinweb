package com.dudu.weixin.service;

import org.springframework.stereotype.Service;

//TODO 需调用CommonTools的接口
@Service
public class CommonTools {
//获取店管家名称
	public  String getShopName(String shopcode) {

		return "北京经典汽车服务有限公司";
	}
	
	
	public  String getShopListImg(String shopcode){
		return null;
	}




	/**
	 * 获取连锁主店
	 * @return
	 * @throws Exception
	 */
	public  String getMainShopcode(String shopcode) {
		return null;
	}
	
	
	
	
}
