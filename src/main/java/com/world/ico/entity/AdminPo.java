package com.world.ico.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by lsb on 2017/3/5.
 */
@Entity
@Table(name="admin")
public class AdminPo implements Serializable {
    @Id
    @Column(name = "admin_id")
    @GeneratedValue
    private int id;

    @Column(name = "admin_username")
    private String adminUserName;

    @Column(name = "admin_pwd")
    private String adminPwd;

    @Column(name = "admin_tel")
    private Long adminTel;

    @Column(name = "admin_realname")
    private String adminRealName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getAdminRealName() {
        return adminRealName;
    }

    public void setAdminRealName(String adminRealName) {
        this.adminRealName = adminRealName;
    }
}
