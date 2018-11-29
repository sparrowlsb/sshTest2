package com.world.ico.dto;

import java.math.BigDecimal;

/**
 * Created by lsb on 2018/11/29.
 */
public class CurbExchange {

    Integer id;

    String exchangeDate;

    String  exchangeType;

    String  exchangePlatform;

    String type;

    BigDecimal money;

    String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getExchangeDate() {
        return exchangeDate;
    }

    public void setExchangeDate(String exchangeDate) {
        this.exchangeDate = exchangeDate;
    }

    public String getExchangeType() {
        return exchangeType;
    }

    public void setExchangeType(String exchangeType) {
        this.exchangeType = exchangeType;
    }

    public String getExchangePlatform() {
        return exchangePlatform;
    }

    public void setExchangePlatform(String exchangePlatform) {
        this.exchangePlatform = exchangePlatform;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
