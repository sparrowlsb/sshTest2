package com.world.ico.service;

import com.world.ico.entity.AdminPo;

import java.util.List;

/**
 * Created by lsb on 2017/3/5.
 */

public interface AdminService {

    public void saveUsers(List<AdminPo> us);

    public int findAdmin(String  adminName,String adminPwd);

    public List<String> getAllUsernames();

}