package com.dudu.weixin.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dudu.soa.ahi.api.ApiAHIPoint;
import com.dudu.soa.ahi.module.*;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.List;

/** 
 * @author 作者姓名  E-mail: email地址 
 * @version 创建时间：2017年2月9日 上午8:52:47 
 * 类说明 
 */
@Service
public class AHIService {
	@Reference(version = "0.0.1" )
	private ApiAHIPoint apiAHIPoint;

	public List<ResultTotalAHIPoint> queryAllPointByPlateNumber(String plateNumber) {
			String plateNumbers = "";
		try {
			plateNumbers = java.net.URLDecoder.decode(plateNumber, "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		AHIPointParam point = new AHIPointParam();
		point.setId(0);
		point.setPlateNumber(plateNumbers);
		point.setRatio("100");
		List<ResultTotalAHIPoint> list = apiAHIPoint.queryAllPointByPlateNumber(point);
		return list;
	}
	
	public List<ResultAHIClassPoint> queryCarPointOne(String plateNumber){
		String plateNumbers = "";
		try {
			plateNumbers = java.net.URLDecoder.decode(plateNumber, "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		AHIPointParam point = new AHIPointParam();
		point.setId(0);
		point.setPlateNumber(plateNumbers);
		point.setRatio("100");
		List<ResultAHIClassPoint> list = apiAHIPoint.queryClassPointOne(point);
		
//		System.out.println("--------------------ahi queryCarPointOne:"+JSON.toJSONString(list));
		
		return list;
	}
	
	public List<ResultAHIChildClassPoint> queryCarPointTwo(String plateNumber,String id,String ratio){
		String plateNumbers = "";
		try {
			plateNumbers = java.net.URLDecoder.decode(plateNumber, "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		AHIPointParam point = new AHIPointParam();
		point.setId(id);
		point.setPlateNumber(plateNumbers);
		point.setRatio(ratio);
		List<ResultAHIChildClassPoint> list = apiAHIPoint.queryClassPointTwo(point);
		
//		System.out.println("--------------------ahi queryCarPointTwo:"+JSON.toJSONString(list));
		
		return list;
	}
	public List<ResultAHISubClassPoint> querySubclassPoint(String plateNumber,String id){
		AHIPointParam point = new AHIPointParam();
		point.setId(id);
		point.setPlateNumber(plateNumber);
		point.setRatio("100");
		List<ResultAHISubClassPoint> list = apiAHIPoint.querySubclassPoint(point);
		return list;
	}
}
