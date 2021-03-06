package com.world.ico.entity;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by lsb on 2018/10/27.
 */
@Entity
@Table(name="CURB_EXCHANGE")
public class CurbExchangePo {
    @Id
    @Column(name = "id")
    @GeneratedValue
    Integer id;

    @Column(name = "user_id")
    Integer userId;

    @Column(name = "exchange_date")
    String exchangeDate;

    @Column(name = "exchange_platform")
    String  exchangePlatform;

    @Column(name = "exchange_type")
    String  exchangeType;


    @Column(name = "type")
    String type;

    @Column(name = "money")
    BigDecimal money;

    @Column(name = "status")
    Integer status;

    public String getExchangePlatform() {
        return exchangePlatform;
    }

    public void setExchangePlatform(String exchangePlatform) {
        this.exchangePlatform = exchangePlatform;
    }

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
