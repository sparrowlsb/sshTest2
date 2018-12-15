package com.world.ico.service;

/**
 * Created by lsb on 2018/9/27.
 */
public interface UserInfoService {

    public String getPersonCode(String  email);
    public void setPersonCode(String personCode,String  email);
}
