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
 * 原 YangCheInfoService
 */
@Service
public class YangCheInfoService {
    //对于盟养车信息的列表查询
    @Reference(version = "0.0.1")
    private ApiYangCheInfoIntf apiYangCheInfoIntf;
    @Reference(version = "1.0")
    private ApiDuduFilesIntf apiDuduFilesIntf;

    public  List<YangCheInfo> queryInfoList(String shopcode) {
        System.out.println("shopcode:"+shopcode);
         //获取图片地址的Api
        YangCheInfo yangCheInfo = new YangCheInfo();
        QueryFilesParam queryFilesParam=new QueryFilesParam();
        yangCheInfo.setLMCode(shopcode);
        List<YangCheInfo> list = apiYangCheInfoIntf.queryList(yangCheInfo);
        queryFilesParam.setShopCode(shopcode);
        for (int i = 0; i < list.size(); i++) {
            if (null==list.get(i).getImgURL()||"".equals(list.get(i).getImgURL())) {
                list.get(i).setImgURL("../img/weijiazai.png");
            }else{
                queryFilesParam.setBusinessConfigId(14);
                queryFilesParam.setOrderCode(list.get(i).getImgURL());
                List<DuduFile> files= apiDuduFilesIntf.queryFiles(queryFilesParam);
                for (DuduFile file : files) {
                    list.get(i).setImgURL(file.getFileUrl());
                }
            }
        }
        return list;
    }
    //对于联盟养车信息的单查
    public YangCheInfo getInfo(Integer id){
        YangCheInfo yangCheInfo = new YangCheInfo();
        yangCheInfo.setId(id);
        YangCheInfo  yangCheInfo1 = apiYangCheInfoIntf.getInfo(yangCheInfo);
        return yangCheInfo1;
    }
}
