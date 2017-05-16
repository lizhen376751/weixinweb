package com.dudu.weixin.shopweiixin.mould;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试控制器
 *
 * @author smile2014
 */
@Controller
public class TestController {
    /**
     * @return
     */
    @RequestMapping("/")
    public String index() {
        return "test";
    }

    /**
     * 查询订单列表
     *
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/query"})
    @ResponseBody
    public Object query(Model model, Integer pageNo) throws Exception {
        System.out.println("pageNo=" + pageNo);
        if (pageNo == null) {
            pageNo = 1;
        }
        List<DataDto> datas = new ArrayList<DataDto>();
        for (int i = pageNo * 15; i < (pageNo + 1) * 15; i++) {
            DataDto data = new DataDto("10000" + i, "10:" + i, "17." + i);
            datas.add(data);
        }
        System.out.println("datas=" + datas);
        return datas;
    }

}



