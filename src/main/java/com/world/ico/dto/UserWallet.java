package com.world.ico.dto;

/**
 * Created by lsb on 2018/10/16.
 */
public class UserWallet {
    Integer  userId;
    Double sellMoney;
    Double totalMoney;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Double getSellMoney() {
        return sellMoney;
    }

    public void setSellMoney(Double sellMoney) {
        this.sellMoney = sellMoney;
    }

    public Double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Double totalMoney) {
        this.totalMoney = totalMoney;
    }
}
