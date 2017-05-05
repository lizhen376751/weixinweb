package com.dudu.weixin.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dudu.soa.weixindubbo.loginlog.api.ApiLogInLog;
import com.dudu.soa.weixindubbo.loginlog.module.LogInLog;
import org.springframework.stereotype.Service;

/**
 * 登录历史记录
 * Created by lizhen on 2017/4/1.
 */
@Service
public class LogInLogService {
    /**
     * 引入登录历史记录接口
     */
    @Reference(version = "1.0")
    private ApiLogInLog apiLogInLog;

    /**
     * 新增登录记录
     *
     * @param plateNumber 车牌号
     * @param lmcode      联盟code
     * @param openid      微信openid
     * @param nickname    微信昵称
     */
    public Integer addLogInLog(String plateNumber, String lmcode, String openid, String nickname) {
        LogInLog logInLog = new LogInLog();
        logInLog.setOpenid(openid);
        //先将之前的openid登录记录删除,保证一个openid同一时间只能登录一个账号
        apiLogInLog.deleLogInLog(logInLog);
        logInLog.setPlateNumber(plateNumber);
        logInLog.setLmcode(lmcode);
        logInLog.setNickname(nickname);
        //之后新增登录信息
        Integer integer = apiLogInLog.addLogInLog(logInLog);
        return integer;
    }

    /**
     * 根据联盟code与openid获取登录记录
     *
     * @param lmcode 联盟code
     * @param openid 微信openid
     * @return LogInLog 短信记录
     */
    public LogInLog getLogInLog(String lmcode, String openid) {
        LogInLog logInLog = new LogInLog();
        logInLog.setLmcode(lmcode);
        logInLog.setOpenid(openid);
        LogInLog logInLog1 = apiLogInLog.getLogInLog(logInLog);
        return logInLog1;
    }

    /**
     * 注销登录记录
     *
     * @param openid 微信openid
     */
    public void deleLogInLog(String openid) {
        LogInLog logInLog = new LogInLog();
        logInLog.setOpenid(openid);
        apiLogInLog.deleLogInLog(logInLog);
    }


}
