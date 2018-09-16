package com.world.ico.service;

/**
 * Created by lsb on 2018/9/8.
 */

public interface LoginService {
    public int findUser(String  user,String password);
    public int findEmail(String  user,String password);
    public int findUserByUsername(String user);
    public int findUserByEmail(String eamil);


    public void addUser(String eamil, String  user,String password);

}
