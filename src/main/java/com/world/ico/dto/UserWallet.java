package com.world.ico.dto;

import java.math.BigDecimal;

/**
 * Created by lsb on 2018/10/16.
 */
public class UserWallet {
    Integer  userId;
    BigDecimal sellMoney;
    BigDecimal totalMoney;

    public Integer getUserId() {
        return userId;
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
