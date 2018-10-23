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
    public int updateBankInfo(String bankName, String bankId,Integer userId) {
        userInfoDao.updateBankInfo(bankName,bankId,userId);
        return 1;
    }

    @Override
    public int updateWechatInfo(String wechatName, String wechatId,Integer userId) {
        userInfoDao.updateWechatInfo(wechatName,wechatId,userId);
        return 1;
    }

    @Override
    public int updateAlipayInfo(String alipayName, String alipayID,Integer userId) {
        userInfoDao.updateAlipayInfo(alipayName,alipayID,userId);
        return 1;
    }

    @Override
    public String getPersonCode(String email) {

        return userInfoDao.getPersonCode(email);
    }

    @Override
    public void setPersonCode(String personCode, String email) {
        userInfoDao.setPersonCode(personCode,email);
    }
}
