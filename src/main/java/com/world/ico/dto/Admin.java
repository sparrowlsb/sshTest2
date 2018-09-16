package com.world.ico.dto;

/**
 * Created by lsb on 2017/3/17.
 */
public class Admin {

    private String adminUserName;


    private String adminPwd;


    private Long adminTel;


    private String adminNick;

    public String getAdminUserName() {
        return adminUserName;
    }

    public void setAdminUserName(String adminUserName) {
        this.adminUserName = adminUserName;
    }

    public String getAdminPwd() {
        return adminPwd;
    }

    public void setAdminPwd(String adminPwd) {
        this.adminPwd = adminPwd;
    }

    public Long getAdminTel() {
        return adminTel;
    }

    public void setAdminTel(Long adminTel) {
        this.adminTel = adminTel;
    }

    public String getAdminNick() {
        return adminNick;
    }

    public void setAdminNick(String adminNick) {
        this.adminNick = adminNick;
    }
}
