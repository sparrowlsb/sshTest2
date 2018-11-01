package com.world.ico.dto;

import java.math.BigDecimal;

/**
 * Created by lsb on 2018/10/8.
 */
public class FundPrice {

    Integer fundId;

    String  fundName;

    BigDecimal fundIncrease;

    BigDecimal fundPrice;

    BigDecimal fundInMoney;

    BigDecimal fundOutMoney;

    BigDecimal fundTotalMoney;

    String fundDate;

    public Integer getFundId() {
        return fundId;
    }

    public void setFundId(Integer fundId) {
        this.fundId = fundId;
    }

    public String getFundName() {
        return fundName;
    }

    public void setFundName(String fundName) {
        this.fundName = fundName;
    }

    public BigDecimal getFundIncrease() {
        return fundIncrease;
    }

    public void setFundIncrease(BigDecimal fundIncrease) {
        this.fundIncrease = fundIncrease;
    }

    public BigDecimal getFundPrice() {
        return fundPrice;
    }

    public void setFundPrice(BigDecimal fundPrice) {
        this.fundPrice = fundPrice;
    }

    public BigDecimal getFundInMoney() {
        return fundInMoney;
    }

    public void setFundInMoney(BigDecimal fundInMoney) {
        this.fundInMoney = fundInMoney;
    }

    public BigDecimal getFundOutMoney() {
        return fundOutMoney;
    }

    public void setFundOutMoney(BigDecimal fundOutMoney) {
        this.fundOutMoney = fundOutMoney;
    }

    public BigDecimal getFundTotalMoney() {
        return fundTotalMoney;
    }

    public void setFundTotalMoney(BigDecimal fundTotalMoney) {
        this.fundTotalMoney = fundTotalMoney;
    }

    public String getFundDate() {
        return fundDate;
    }

    public void setFundDate(String fundDate) {
        this.fundDate = fundDate;
    }
}
