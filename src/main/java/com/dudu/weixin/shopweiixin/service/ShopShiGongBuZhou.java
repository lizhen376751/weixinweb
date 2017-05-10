package com.dudu.weixin.shopweiixin.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dudu.soa.salescenter.workcomplate.api.ApiShopWorkComplateIntf;
import com.dudu.soa.salescenter.workcomplate.module.EdbWorkComplateMessage;
import com.dudu.soa.salescenter.workcomplate.module.EdbWorkComplateQueryModule;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 施工步骤的查看
 * Created by lizhen on 2017/5/9.
 */
@Service
public class ShopShiGongBuZhou {
    /**
     * 施工步骤接口
     */
    @Reference(version = "0.0.1")
    private ApiShopWorkComplateIntf apiShopWorkComplateIntf;


    /**
     * 查看施工步骤,进行数据组装
     *
     * @param shopCode      店铺编码
     * @param wxpingzheng   工单号
     * @param xiangmubianma 项目编码
     * @return 施工步骤的集合
     */
    public List<EdbWorkComplateMessage> queryListShiGongBuZhou(String shopCode, String wxpingzheng, String xiangmubianma) {
        List<EdbWorkComplateMessage> queryShiGongBuZhou = null;
        String[] sourceStrArray = xiangmubianma.split("_");
        for (int i = 0; i < sourceStrArray.length; i++) {
            String sourceStrArray2 = sourceStrArray[i];
            String[] sourceStrArray3 = sourceStrArray2.split("-");
            String projectCode = sourceStrArray3[0];
            String projectId = sourceStrArray3[1];
            EdbWorkComplateMessage edbWorkComplateMessage = this.queryShiGongBuZhou(shopCode, wxpingzheng, projectCode, projectId);
            queryShiGongBuZhou.add(edbWorkComplateMessage);
        }
        return queryShiGongBuZhou;
    }

    /**
     * 查看施工步骤
     *
     * @param projectCode 项目编码
     * @param projectId   店管家项目id
     * @param shopCode    店铺编码
     * @param wxpingzheng 工单号
     * @return 施工步骤的集合
     */
    public EdbWorkComplateMessage queryShiGongBuZhou(String shopCode, String wxpingzheng, String projectCode, String projectId) {
        EdbWorkComplateQueryModule edbWorkComplateQueryModule = new EdbWorkComplateQueryModule();
        edbWorkComplateQueryModule.setShopCode(shopCode);
        Long projectid = null;
        if (projectId != null && !projectId.equals("")) {
            projectid = Long.parseLong(projectId);
        }
        edbWorkComplateQueryModule.setProjectId(projectid);
        edbWorkComplateQueryModule.setItemCode(projectCode);
        edbWorkComplateQueryModule.setOrderCode(wxpingzheng);
        List<EdbWorkComplateMessage> workComplateMessage4Image = apiShopWorkComplateIntf.getWorkComplateMessage4Image(edbWorkComplateQueryModule);
        if (workComplateMessage4Image != null && workComplateMessage4Image.size() > 0) {
            return workComplateMessage4Image.get(0);
        }
        return null;
    }

}
