package com.dudu.weixin.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dudu.soa.lmbasedata.basedata.introduted.api.ApiIntroducedIntf;
import com.dudu.soa.lmbasedata.basedata.introduted.module.Introduced;
import org.springframework.stereotype.Service;

/**
 * 联盟介绍
 * Created by lizhen on 2017/3/15.
 */
@Service
public class LianmengIntroducedService {
    /**
     *@Autowired lizhen
     * @Title 联盟介绍
     */
    @Reference(owner = "lizhen")
    private  ApiIntroducedIntf apiIntroducedIntf;

    /**
     * @Title 联盟介绍
     * @Autowired lizhen
     * @param shopcode shopcode
     * @return Introduced 联盟介绍的实体
     */

    public Introduced queryEntry(String shopcode) {
        Introduced introduced = apiIntroducedIntf.queryEntry(shopcode);
        return introduced;
    }
}
