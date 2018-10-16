package com.world.ico.service.serviceImpl;

import com.world.ico.dao.LoginDao;
import com.world.ico.dao.PasswordDao;
import com.world.ico.entity.UserPo;
import com.world.ico.service.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by lsb on 2018/10/16.
 */
@Transactional
@Service
public class PasswordServiceImpl implements PasswordService{
    @Autowired
    public PasswordDao passwordDao;
    @Autowired
    public LoginDao loginDao;

    @Override
    public void changePassword(String newPassword, String email) {
        passwordDao.updatePassword(newPassword,email);

    }

    @Override
    public void changeFinancePassword(String newPasswordFinance, String email) {
        passwordDao.updateFinancePassword(newPasswordFinance,email);
    }

    @Override
    public void forgetFinancePassword(String entryPassword ,String newPasswordFinance, String email) {

        List<UserPo> iterable =loginDao.getUserByEmailAndPassword(email,entryPassword);

        if(iterable.size()==1){
            passwordDao.updateFinancePassword(newPasswordFinance,email);
        }

    }
}
