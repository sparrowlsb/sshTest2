package com.world.ico.service;

/**
 * Created by lsb on 2018/9/27.
 */
public interface UserInfoService {

    public int updateBankInfo(String bankName,String bankId,Integer userId);
    public int updateWechatInfo(String  wechatName,String wechatId,Integer userId);
    public int updateAlipayInfo(String  alipayName,String alipayID,Integer userId);
    public String getPersonCode(String  email);
    public void setPersonCode(String personCode,String  email);
}
