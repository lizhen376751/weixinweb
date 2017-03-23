package com.dudu.weixin.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dudu.soa.lmk.customer.api.ApiCustomerIntf;
import com.dudu.soa.lmk.customer.module.QueryCustomerParam;
import com.dudu.soa.lmk.customer.module.ResultQueryCustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class LoginActionNewService {
    @Autowired
    private HttpSession session;
    @Reference(version = "0.0.1")
    private ApiCustomerIntf apiCustomerIntf;

    public boolean login(HttpServletRequest request) {
        boolean flg = false;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String CarId1 = request.getParameter("CarId");
        String CarId = CarId1.toUpperCase();
        String Phone = request.getParameter("Phone");
        String shopcode = request.getParameter("shopcode");
        String OpenId = request.getParameter("OpenId");

        System.out.println("-------------登陆车牌：" + CarId + "|Phone:" + Phone + "|");
        QueryCustomerParam queryCustomerParam = new QueryCustomerParam();
        queryCustomerParam.setCarHaopai(CarId);

        List<ResultQueryCustomer> result = apiCustomerIntf.queryCustomerStructureClass(queryCustomerParam);
        if (result != null && result.size() > 0) {
            ResultQueryCustomer resultQueryCustomer = result.get(0);
            String trueMobile = resultQueryCustomer.getCustomerMobile();
            System.out.println("-------------登陆验证：" + CarId + "|Phone:" + Phone + "|trueMobile:" + trueMobile + "|");
            if (Phone.equals(trueMobile)) {
                session.setAttribute("DUDUCHEWANG_CarId", CarId);
                session.setAttribute("DUDUCHEWANG_OpenId", OpenId);
                session.setAttribute("DUDUCHEWANG_shopcode", shopcode);
                //TODO 后期封装成接口
                //登陆后记录车辆openId
//					if (OpenId != null && !"null".equals(OpenId)&& !"".equals(OpenId) && CarId!=null && !"null".equals(CarId) && !"".equals(CarId)) {
//						ArrayList<HashMap<String, String>> checkOpenList = dblm.query(" select id from DDCW_WX_CustOpenId where lianMengCode='"+shopcode+"' and carNo='"+CarId+"' ");
//						if(checkOpenList!=null && checkOpenList.size()>0){
//							dblm.update("update DDCW_WX_CustOpenId set openId='"+OpenId+"',updateTime=getdate() where lianMengCode='"+shopcode+"' and carNo='"+CarId+"'");
//						}else{
//							dblm.update("insert into DDCW_WX_CustOpenId(lianMengCode,carNo,openId,firstOpenId)values('"+shopcode+"','"+CarId+"','"+OpenId+"','"+OpenId+"')");
//						}
//					}

                flg = true;
            }

        }
        return flg;

    }

    //修改密码
    public int editpasswod(HttpServletRequest request) {
        String CarId1 = request.getParameter("CarId");
        String CarId = CarId1.toUpperCase();
        String Phone = request.getParameter("Phone");
        String shopcode = request.getParameter("shopcode");
        String newPhone = request.getParameter("newPhone");
        int flg = 0;
        //TODO 封装接口调用服务
        return flg;
    }
}
