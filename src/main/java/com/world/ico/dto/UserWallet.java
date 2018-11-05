package com.world.ico.dto;

import java.math.BigDecimal;

/**
 * Created by lsb on 2018/10/16.
 */
public class UserWallet {
    Integer  userId;
    BigDecimal sellMoney;
    BigDecimal totalMoney;
    String type;
    BigDecimal money;
    BigDecimal count;

    public Integer getUserId() {
        return userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public BigDecimal getCount() {
        return count;
    }

    public void setCount(BigDecimal count) {
        this.count = count;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public BigDecimal getSellMoney() {
        return sellMoney;
    }

    public void setSellMoney(BigDecimal sellMoney) {
        this.sellMoney = sellMoney;
    }

    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }
}
