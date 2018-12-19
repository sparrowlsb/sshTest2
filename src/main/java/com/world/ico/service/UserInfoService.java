package com.world.ico.service;

import com.world.ico.dto.User;
import com.world.ico.dto.UserInfo;

/**
 * Created by lsb on 2018/9/27.
 */
public interface UserInfoService {
    public UserInfo getPersonInfo(String  email);
    public void updatePersonInfo(String  email,UserInfo userInfo);
}
