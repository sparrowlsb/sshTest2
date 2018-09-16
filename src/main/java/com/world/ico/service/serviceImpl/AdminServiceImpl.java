package com.world.ico.service.serviceImpl;

import com.world.ico.entity.AdminPo;
import com.world.ico.service.AdminService;
import com.world.ico.dao.AdminDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lsb on 2017/3/5.
 */
@Service("AdminService")
@Transactional
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDAO adminDAO;


    @Override
    public void saveUsers(List<AdminPo> us) {

    }

    public int findAdmin(String adminName, String adminPwd) {

        return 0;
    }


    public List<String> getAllUsernames() {
        Iterable<AdminPo> iterable=adminDAO.findAll();
        List<String> a=new ArrayList<>();
        for (AdminPo po:iterable){
            a.add(po.getAdminRealName());
        }
        return  a;
    }

}