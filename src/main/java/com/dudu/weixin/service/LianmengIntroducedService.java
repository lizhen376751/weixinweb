package com.dudu.weixin.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dudu.soa.lmbasedata.basedata.introduted.api.ApiIntroducedIntf;
import com.dudu.soa.lmbasedata.basedata.introduted.module.Introduced;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/3/15.
 */
@Service
public class LianmengIntroducedService {
    @Reference(version = "0.0.1",owner = "lizhen")
    private  ApiIntroducedIntf apiIntroducedIntf;
    //联盟介绍
    public Introduced queryEntry(String shopcode){
        Introduced introduced = apiIntroducedIntf.queryEntry(shopcode);
        return introduced;
    }
}
