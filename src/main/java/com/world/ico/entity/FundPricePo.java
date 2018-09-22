package com.world.ico.entity;

import javax.persistence.*;
import java.security.Timestamp;

/**
 * Created by lsb on 2018/9/22.
 */
@Entity
@Table(name="FUND_PRICE")
public class FundPricePo {
    @Id
    @Column(name = "id")
    @GeneratedValue
    Integer id;

    @Column(name = "date")
    Timestamp date;

    @Column(name = "today_price")
    Double todayPrice;

    @Column(name = "today_increase")
    Double todayIncrease;

    @Column(name = "hist_increase")
    Double histIncrease;

    @Column(name = "total_money")
    Double totalMoney;

    @Column(name = "today_inmoney")
    Double todayInmoney;

    @Column(name = "today_outmoney")
    Double todayOutmoney;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Double getTodayPrice() {
        return todayPrice;
    }

    public void setTodayPrice(Double todayPrice) {
        this.todayPrice = todayPrice;
    }

    public Double getTodayIncrease() {
        return todayIncrease;
    }

    public void setTodayIncrease(Double todayIncrease) {
        this.todayIncrease = todayIncrease;
    }

    public Double getHistIncrease() {
        return histIncrease;
    }

    public void setHistIncrease(Double histIncrease) {
        this.histIncrease = histIncrease;
    }

    public Double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public Double getTodayInmoney() {
        return todayInmoney;
    }

    public void setTodayInmoney(Double todayInmoney) {
        this.todayInmoney = todayInmoney;
    }

    public Double getTodayOutmoney() {
        return todayOutmoney;
    }

    public void setTodayOutmoney(Double todayOutmoney) {
        this.todayOutmoney = todayOutmoney;
    }
}
