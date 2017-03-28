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
    /**
     * 常量 12
     */
    private final int num = 13;
    /**
     *引入联盟活动接口
     */
    @Reference(version = "0.0.1")
    private ApiLMActivityInft apiLMActivityInft;
    /**
     *引入上传图片的接口
     */
    @Reference(version = "1.0")
    private ApiDuduFilesIntf apiDuduFilesIntf;

    /**
     * @Title 对于联盟活动信息的列表查询
     * @param shopcode 店铺代码
     * @return 联盟活动列表
     * @Autowired lizhen
     */

    public List<LMActivity> queryInfoList(String shopcode) {
        LMActivity lmActivity = new LMActivity();
        lmActivity.setLMCode(shopcode);
        QueryFilesParam queryFilesParam = new QueryFilesParam();
        List<LMActivity> list = apiLMActivityInft.queryList(lmActivity);
        queryFilesParam.setShopCode(shopcode);
        for (int i = 0; i < list.size(); i++) {
            if (null == list.get(i).getImgURL() || "".equals(list.get(i).getImgURL())) {
                list.get(i).setImgURL("../img/weijiazai.png");
            } else {
                queryFilesParam.setBusinessConfigId(num);
                queryFilesParam.setOrderCode(list.get(i).getImgURL());
                List<DuduFile> files = apiDuduFilesIntf.queryFiles(queryFilesParam);
                for (DuduFile file : files) {
                    list.get(i).setImgURL(file.getFileUrl());
                }
            }
        }
        return list;
    }

    /**
     * @param id 信息主键id
     * @return LMActivity联盟活动实体
     * @Title 对于联盟活动信息的单查
     */

    public LMActivity getInfo(Integer id) {

        LMActivity lmActivity = new LMActivity();
        lmActivity.setId(id);
        LMActivity lmActivity1 = apiLMActivityInft.getActivity(lmActivity);
        return lmActivity1;
    }

}
