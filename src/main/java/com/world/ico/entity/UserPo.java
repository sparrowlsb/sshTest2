package com.world.ico.entity;

import javax.persistence.*;

/**
 * Created by lsb on 2018/9/8.
 */
@Entity
@Table(name="USER")
public class UserPo {
    @Id
    @Column(name = "user_id")
    @GeneratedValue
    Integer userId;

    @Column(name = "email")
    String email;

    @Column(name = "name")
    String name;

    @Column(name = "password")
    String password;

    @Column(name = "person_code")
    String personCode;

    @Column(name = "usdt_address")
    String usdtAddress;

    @Column(name = "status")
    Integer status;

    public String getPersonCode() {
        return personCode;
    }

    public void setPersonCode(String personCode) {
        this.personCode = personCode;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getUsdtAddress() {
        return usdtAddress;
    }

    public void setUsdtAddress(String usdtAddress) {
        this.usdtAddress = usdtAddress;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
