package com.dudu.weixin.shopweiixin.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dudu.soa.lmk.basicdata.project.api.ApiProjectIntf;
import com.dudu.soa.lmk.basicdata.project.module.ProjectProcessConfigSaveModule;
import com.dudu.soa.salescenter.workcomplate.api.ApiShopWorkComplateIntf;
import com.dudu.soa.salescenter.workcomplate.module.EdbWorkComplateMessage;
import com.dudu.soa.salescenter.workcomplate.module.EdbWorkComplateQueryModule;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 施工步骤与标准流程的查看
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
     * 引入标准流程查看接口
     */
    @Reference(version = "0.0.1")
    private ApiProjectIntf apiProjectIntf;

    /**
     * 查看施工步骤,进行数据组装
     *
     * @param request 请求
     * @return 施工步骤的集合
     */
    public List<List<EdbWorkComplateMessage>> queryListShiGongBuZhou(HttpServletRequest request) {
        String shopCode = request.getParameter("shopCode"); //店铺编码
        String wxpingzheng = request.getParameter("wxpingzheng"); //维修单号
        String xiangmubianma = request.getParameter("xunumber"); //项目字符串
        List<List<EdbWorkComplateMessage>> lists = new ArrayList<List<EdbWorkComplateMessage>>();

        List<EdbWorkComplateMessage> queryShiGongBuZhou = null;
        String[] sourceStrArray = xiangmubianma.split("_");
        for (int i = 0; i < sourceStrArray.length; i++) {
            String sourceStrArray2 = sourceStrArray[i];
            String[] sourceStrArray3 = sourceStrArray2.split("-");
            String projectCode = sourceStrArray3[0];
            String projectId = sourceStrArray3[1];
            List<EdbWorkComplateMessage> edbWorkComplateMessages = this.queryShiGongBuZhou(shopCode, wxpingzheng, projectCode, projectId);
            if (edbWorkComplateMessages != null) {
                lists.add(edbWorkComplateMessages);
            }
        }
        return lists;
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
    public List<EdbWorkComplateMessage> queryShiGongBuZhou(String shopCode, String wxpingzheng, String projectCode, String projectId) {
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
            return workComplateMessage4Image;
        }
        return null;
    }

    /**
     * 查询标准流程
     *
     * @param request 请求
     * @return 标准流程图片
     */
    public List<ProjectProcessConfigSaveModule> queryProjectProcess(HttpServletRequest request) {
        String itemCode = request.getParameter("itemCode"); //项目编码
        String shopCodeLm = request.getParameter("shopCodeLm"); //品牌商编码
        ProjectProcessConfigSaveModule projectProcessConfigSaveModule = new ProjectProcessConfigSaveModule();
        projectProcessConfigSaveModule.setBrandCode(shopCodeLm);
        projectProcessConfigSaveModule.setItemCode(itemCode);
        projectProcessConfigSaveModule.setNeedPhotos(true);
        List<ProjectProcessConfigSaveModule> projectProcessConfigSaveModules = apiProjectIntf.queryProjectProcess(projectProcessConfigSaveModule);
        return projectProcessConfigSaveModules;
    }
}
