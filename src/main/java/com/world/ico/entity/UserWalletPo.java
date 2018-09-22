package com.world.ico.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by lsb on 2018/9/22.
 */
public class UserWalletPo {
    @Id
    @Column(name = "id")
    @GeneratedValue
    Integer id;

    @Column(name = "user_id")
    Integer userId;

    @Column(name = "total_assets")
    Double totalAssets;

    @Column(name = "rmb")
    Double rmb;

    @Column(name = "fund_id")
    Integer fundId;

    @Column(name = "fund_money")
    Double fundMoney;

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

    public Double getTotalAssets() {
        return totalAssets;
    }

    public void setTotalAssets(Double totalAssets) {
        this.totalAssets = totalAssets;
    }

    public Double getRmb() {
        return rmb;
    }

    public void setRmb(Double rmb) {
        this.rmb = rmb;
    }

    public Integer getFundId() {
        return fundId;
    }

    public void setFundId(Integer fundId) {
        this.fundId = fundId;
    }

    public Double getFundMoney() {
        return fundMoney;
    }

    public void setFundMoney(Double fundMoney) {
        this.fundMoney = fundMoney;
    }
}
