package com.world.ico.dto;

/**
 * Created by lsb on 2018/9/26.
 */
public class UserInfo {

    Integer userId;

    String email;

    String alipayId;

    String allipayName;

    String wechatId;

    String wechatName;

    String bankId;

    String bankName;

    String userInfoCode;

    public String getUserInfoCode() {
        return userInfoCode;
    }

    public void setUserInfoCode(String userInfoCode) {
        this.userInfoCode = userInfoCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getAlipayId() {
        return alipayId;
    }

    public void setAlipayId(String alipayId) {
        this.alipayId = alipayId;
    }

    public String getAllipayName() {
        return allipayName;
    }

    public void setAllipayName(String allipayName) {
        this.allipayName = allipayName;
    }

    public String getWechatId() {
        return wechatId;
    }

    public void setWechatId(String wechatId) {
        this.wechatId = wechatId;
    }

    public String getWechatName() {
        return wechatName;
    }

    public void setWechatName(String wechatName) {
        this.wechatName = wechatName;
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
}
