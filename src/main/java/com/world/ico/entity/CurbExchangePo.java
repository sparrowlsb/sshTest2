package com.world.ico.entity;

import javax.persistence.*;
import java.sql.Timestamp;

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
    Timestamp exchangeDate;

    @Column(name = "exchange_type")
    String  exchangeType;

    @Column(name = "type")
    String type;

    @Column(name = "money")
    Double money;

    @Column(name = "status")
    Integer status;

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

    public Timestamp getExchangeDate() {
        return exchangeDate;
    }

    public void setExchangeDate(Timestamp exchangeDate) {
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

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
