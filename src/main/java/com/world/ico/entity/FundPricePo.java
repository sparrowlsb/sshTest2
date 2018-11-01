package com.world.ico.entity;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by lsb on 2018/9/22.
 */

@Entity
@Table(name="FUND_PRICE")
public class FundPricePo {
    @Id
    @Column(name = "fund_id")
    @GeneratedValue
    Integer fundId;

    @Column(name = "today_price")
    BigDecimal todayPrice;

    @Column(name = "total_money")
    BigDecimal totalMoney;

    @Column(name = "date")
    String date;

    @Column(name = "today_inmoney")
    BigDecimal todayInmoney;

    @Column(name = "today_outmoney")
    BigDecimal todayOutmoney;

    public Integer getFundId() {
        return fundId;
    }

    public void setFundId(Integer fundId) {
        this.fundId = fundId;
    }

    public BigDecimal getTodayPrice() {
        return todayPrice;
    }

    public void setTodayPrice(BigDecimal todayPrice) {
        this.todayPrice = todayPrice;
    }

    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public BigDecimal getTodayInmoney() {
        return todayInmoney;
    }

    public void setTodayInmoney(BigDecimal todayInmoney) {
        this.todayInmoney = todayInmoney;
    }

    public BigDecimal getTodayOutmoney() {
        return todayOutmoney;
    }

    public void setTodayOutmoney(BigDecimal todayOutmoney) {
        this.todayOutmoney = todayOutmoney;
    }
}
