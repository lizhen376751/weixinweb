package com.dudu.weixin.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dudu.soa.dududata.oss.api.ApiDuduFilesIntf;
import com.dudu.soa.dududata.oss.module.DuduFile;
import com.dudu.soa.dududata.oss.module.param.QueryFilesParam;
import com.dudu.soa.lmbasedata.basedata.yangcheinfo.api.ApiYangCheInfoIntf;
import com.dudu.soa.lmbasedata.basedata.yangcheinfo.module.YangCheInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/3/15.
 * 鍘� YangCheInfoService
 */
@Service
public class YangCheInfoService {
    /**
     * 养车信息接口
     */
    @Reference(version = "0.0.1")
    private ApiYangCheInfoIntf apiYangCheInfoIntf;
    /**
     * 图片地址
     */
    @Reference(version = "1.0")
    private ApiDuduFilesIntf apiDuduFilesIntf;
    /**
     * 常量
     */
    private final int num = 14;
    /**
     * @param shopcode 店铺代码
     * @return 养车信息列表
     */

    public List<YangCheInfo> queryInfoList(String shopcode) {
        System.out.println("shopcode:" + shopcode);

        YangCheInfo yangCheInfo = new YangCheInfo();
        QueryFilesParam queryFilesParam = new QueryFilesParam();
        yangCheInfo.setLMCode(shopcode);
        List<YangCheInfo> list = apiYangCheInfoIntf.queryList(yangCheInfo);
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
     *
     * @param id id
     * @return 养车信息
     */
    public YangCheInfo getInfo(Integer id) {
        YangCheInfo yangCheInfo = new YangCheInfo();
        yangCheInfo.setId(id);
        YangCheInfo yangCheInfo1 = apiYangCheInfoIntf.getInfo(yangCheInfo);
        return yangCheInfo1;
    }
}
