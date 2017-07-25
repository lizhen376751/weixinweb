package com.dudu.weixin.control;

import com.dudu.soa.framework.dudusoahelp.DuduSOAHelp;
import com.dudu.soa.framework.pagehelp.PageParams;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 分页查询拦截器
 * Created by lizhen on 2017/5/16.
 */

public class PagingQueryInterceptor implements HandlerInterceptor {
    /**
     * 获取第几页的值
     *
     * @param value 第几页
     * @return 第几页
     */
    private Integer getInteger(String value) {
        return null == value || "".equals(value) ? null : Integer.parseInt(value);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        try {
            Integer currentPage = getInteger(request.getParameter("page"));
            Integer pageSize = getInteger(request.getParameter("rows"));
            String sortStr = request.getParameter("sidx") + " " + request.getParameter("sord");

            PageParams requestParam = new PageParams();
            requestParam.setCurrentPage(currentPage);
            requestParam.setSortStr(sortStr);
            requestParam.setPageSize(pageSize);
            DuduSOAHelp.setLocalRequestPageparams(requestParam);
            DuduSOAHelp.setActionType(request.getParameter("ACTIONTYPE"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println(Thread.currentThread().getName());
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println(Thread.currentThread().getName());
    }
}
