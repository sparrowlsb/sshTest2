package com.world.ico.dto;

/**
 * Created by lsb on 2018/12/19.
 */
public class UserInfo {
    Integer userId;

    String name;

    String email;

    String usdtAddress;

    String personCode;

    String idCardOn;

    String idCardUnder;

    Integer status;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsdtAddress() {
        return usdtAddress;
    }

    public void setUsdtAddress(String usdtAddress) {
        this.usdtAddress = usdtAddress;
    }

    public String getPersonCode() {
        return personCode;
    }

    public void setPersonCode(String personCode) {
        this.personCode = personCode;
    }

    public String getIdCardOn() {
        return idCardOn;
    }

    public void setIdCardOn(String idCardOn) {
        this.idCardOn = idCardOn;
    }

    public String getIdCardUnder() {
        return idCardUnder;
    }

    public void setIdCardUnder(String idCardUnder) {
        this.idCardUnder = idCardUnder;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
