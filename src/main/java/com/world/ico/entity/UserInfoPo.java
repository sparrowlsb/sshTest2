package com.world.ico.entity;

import javax.persistence.*;

/**
 * Created by lsb on 2018/9/22.
 */
@Entity
@Table(name="USER_INFO")
public class UserInfoPo {

    @Id
    @Column(name = "id")
    @GeneratedValue
    Integer id;

    @Column(name = "user_id")
    Integer userId;

    @Column(name = "alipay_id")
    String alipayId;

    @Column(name = "allipay_name")
    String allipayName;

    @Column(name = "wechat_id")
    String wechatId;

    @Column(name = "wechat_name")
    String wechatName;

    @Column(name = "bank_id")
    String bankId;

    @Column(name = "bank_name")
    String bankName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
