package com.world.ico.service;

/**
 * Created by lsb on 2018/10/16.
 */
public interface PasswordService {
    public void changePassword(String newPassword,String email);
    public void changeFinancePassword(String newPasswordFinance,String email);
    public void forgetFinancePassword(String entryPassword ,String newPasswordFinance,String email);
}
