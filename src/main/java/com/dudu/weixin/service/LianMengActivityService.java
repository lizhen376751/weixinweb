package com.dudu.weixin.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dudu.soa.dududata.oss.api.ApiDuduFilesIntf;
import com.dudu.soa.dududata.oss.module.DuduFile;
import com.dudu.soa.dududata.oss.module.param.QueryFilesParam;
import com.dudu.soa.lmbasedata.basedata.lmactivity.api.ApiLMActivityInft;
import com.dudu.soa.lmbasedata.basedata.lmactivity.module.LMActivity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/3/15.
 * 原 LianMengActivityService
 */
@Service
public class LianMengActivityService {
    @Reference(version = "0.0.1")
    private ApiLMActivityInft apiLMActivityInft;

    @Reference(version = "1.0")
    private ApiDuduFilesIntf apiDuduFilesIntf;
    //对于联盟活动信息的列表查询
    public   List<LMActivity> queryInfoList(String shopcode){

        LMActivity lmActivity = new LMActivity();
        lmActivity.setLMCode(shopcode);
        QueryFilesParam queryFilesParam=new QueryFilesParam();
        System.out.println("对于联盟活动信息的列表查询"+shopcode);
        List<LMActivity> list = apiLMActivityInft.queryList(lmActivity);
        queryFilesParam.setShopCode(shopcode);
        for (int i = 0; i < list.size(); i++) {
            if (null==list.get(i).getImgURL()||"".equals(list.get(i).getImgURL())) {
                list.get(i).setImgURL("../img/weijiazai.png");
            }else{
                queryFilesParam.setBusinessConfigId(13);
                queryFilesParam.setOrderCode(list.get(i).getImgURL());
                List<DuduFile> files= apiDuduFilesIntf.queryFiles(queryFilesParam);
                for (DuduFile file : files) {
                    list.get(i).setImgURL(file.getFileUrl());
                }
            }
        }

        return list;
    }
    //对于联盟活动信息的单查
    public LMActivity getInfo(Integer id){

        LMActivity lmActivity = new LMActivity();
        lmActivity.setId(id);
        LMActivity lmActivity1= apiLMActivityInft.getActivity(lmActivity);
        return lmActivity1;
    }

}
