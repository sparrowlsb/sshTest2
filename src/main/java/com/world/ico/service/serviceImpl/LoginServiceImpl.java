package com.world.ico.service.serviceImpl;

import com.world.ico.dao.LoginDao;
import com.world.ico.entity.UserPo;
import com.world.ico.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by lsb on 2018/9/8.
 */
@Transactional
@Service

public class LoginServiceImpl implements LoginService{

    @Autowired
    public LoginDao loginDao;

    @Override
    public int findUser(String email, String password) {
        List<UserPo> iterable =loginDao.getUserByEmailAndPassword(email,password);
        if(iterable.size()==1){
            return 1;
        }else if(iterable.size()>=2){
            return -1;
        }else {
            return 0;
        }
    }


    @Override
    public int findEmailIdByEmail(String email) {
        List<Integer> iterable =loginDao.getUserIdByEmail(email);

        if(iterable.size()==1){
            Integer emailId= iterable.get(0);
            return emailId;
        }else if(iterable.size()>=2){
            return -1;
        }else {
            return 0;
        }
    }


    @Override
    public void addUser(String eamil, String password) {

        loginDao.insertUserByEmailNameAndPassword(eamil,password);


    }

    @Override
    public void addUserInfo(Integer userId) {
        loginDao.insertUserInfoByUserId(userId);
    }

    @Override
    public void addUserWallet(Integer userId) {
        loginDao.insertUserWallet(userId, BigDecimal.valueOf(0.0),"USDT",BigDecimal.valueOf(0.0));
        loginDao.insertUserWallet(userId,BigDecimal.valueOf(0.0),"FUND_1",BigDecimal.valueOf(0.0));
        loginDao.insertUserWallet(userId,BigDecimal.valueOf(0.0),"FUND_2",BigDecimal.valueOf(0.0));
        loginDao.insertUserWallet(userId,BigDecimal.valueOf(0.0),"FUND_3",BigDecimal.valueOf(0.0));
    }


}
