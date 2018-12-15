package com.world.ico.service.serviceImpl;

import com.world.ico.dao.UserInfoDao;
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
    public String getPersonCode(String email) {

        return userInfoDao.getPersonCode(email);
    }

    @Override
    public void setPersonCode(String personCode, String email) {
        userInfoDao.setPersonCode(personCode,email);
    }
}
