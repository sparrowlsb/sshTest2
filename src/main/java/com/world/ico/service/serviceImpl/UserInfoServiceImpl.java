package com.world.ico.service.serviceImpl;

import com.world.ico.dao.UserInfoDao;
import com.world.ico.dto.User;
import com.world.ico.dto.UserInfo;
import com.world.ico.entity.UserPo;
import com.world.ico.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by lsb on 2018/9/27.
 */
@Transactional
@Service
public class UserInfoServiceImpl implements UserInfoService{

    @Autowired
    public UserInfoDao userInfoDao;


    @Override
    public UserInfo getPersonInfo(String email) {

        UserPo userPo=userInfoDao.getUserInfoByEmail(email);
        UserInfo userInfo= new UserInfo();
        if (userPo==null){
            return null;
        }
        else {
            userInfo.setUserId(userPo.getUserId());
            userInfo.setEmail(userPo.getEmail());
            userInfo.setName(userPo.getName());
            userInfo.setPersonCode(userPo.getPersonCode());
            userInfo.setStatus(userPo.getStatus());
            userInfo.setUsdtAddress(userPo.getUsdtAddress());
        }

        return  userInfo;
    }

    @Override
    public void updatePersonInfo(String email,UserInfo userInfo) {
        synchronized (this){
            userInfoDao.updateUserInfoByEmail(email,userInfo.getName(),userInfo.getPersonCode(),userInfo.getIdCardOn(),userInfo.getIdCardUnder(),userInfo.getUsdtAddress());
        }
    }

}
