package com.world.ico.service.serviceImpl;

import com.world.ico.dao.LoginDao;
import com.world.ico.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by lsb on 2018/9/8.
 */
@Transactional
@Service

public class LoginServiceImpl extends BaseImpl implements LoginService{

    @Autowired
    public LoginDao loginDao;

    @Override
    public int findUser(String user, String password) {
//        String passwordSha1=hash(password,"SHA1");
        List<Object[]> iterable =loginDao.getUserByUserAndPassword(user,password);
        if(iterable.size()==1){
            return 1;
        }else if(iterable.size()>=2){
            return 2;
        }else {
            return 0;
        }
    }

    @Override
    public int findEmail(String email, String password) {
        List<Object[]> iterable =loginDao.getUserByEmailAndPassword(email,password);
        if(iterable.size()==1){
            return 1;
        }else if(iterable.size()>=2){
            return 2;
        }else {
            return 0;
        }
    }


    @Override
    public int findUserByUsername(String user) {
        List<Object[]> iterable =loginDao.getUserByUser(user);
        if(iterable.size()==1){
            return 1;
        }else if(iterable.size()>=2){
            return 2;
        }else {
            return 0;
        }
    }

    @Override
    public int findUserByEmail(String eamil) {
        List<Object[]> iterable =loginDao.getUserByEmail(eamil);
        if(iterable.size()==1){
            return 1;
        }else if(iterable.size()>=2){
            return 2;
        }else {
            return 0;
        }
    }


    @Override
    public void addUser(String eamil, String user, String password) {
        loginDao.insertUserByEmailUserAndPassword(eamil,user,password);

    }


}
