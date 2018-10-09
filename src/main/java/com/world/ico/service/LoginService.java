package com.world.ico.service;

/**
 * Created by lsb on 2018/9/8.
 */

public interface LoginService {

    public int findUser(String  email,String password);
    public int findEmailIdByEmail(String eamil);

    public void addUser(String eamil,String password);

    public void addUserInfo(Integer userId);

    public void addUserWallet(Integer userId);

//    public void addUserLog(String eamil, String  user,String password);


}
