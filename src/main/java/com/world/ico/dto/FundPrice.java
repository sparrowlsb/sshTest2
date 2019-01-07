package com.world.ico.dto;

import java.math.BigDecimal;

/**
 * Created by lsb on 2018/10/8.
 */
public class FundPrice {

    Integer fundId;

    String  fundName;

    String  fundType;

    String  fundStartDate;

    String  fundManageName;

    String  fundManageSex;

    Integer  fundManageAge;

    String  fundManageSpeciality;

    String  fundManageInfo;

    BigDecimal fundIncrease;

    BigDecimal fundPrice;

    BigDecimal fundInMoney;

    BigDecimal fundOutMoney;

    BigDecimal fundTotalMoney;

    String fundDate;

    BigDecimal fundQuoteChange;

    BigDecimal fundQuoteHistChange;

    public String getFundStartDate() {
        return fundStartDate;
    }

    public void setFundStartDate(String fundStartDate) {
        this.fundStartDate = fundStartDate;
    }

    public String getFundManageName() {
        return fundManageName;
    }

    public void setFundManageName(String fundManageName) {
        this.fundManageName = fundManageName;
    }

    public String getFundManageSex() {
        return fundManageSex;
    }

    public void setFundManageSex(String fundManageSex) {
        this.fundManageSex = fundManageSex;
    }


    public Integer getFundManageAge() {
        return fundManageAge;
    }

    public void setFundManageAge(Integer fundManageAge) {
        this.fundManageAge = fundManageAge;
    }

    public String getFundManageSpeciality() {
        return fundManageSpeciality;
    }

    public void setFundManageSpeciality(String fundManageSpeciality) {
        this.fundManageSpeciality = fundManageSpeciality;
    }

    public String getFundManageInfo() {
        return fundManageInfo;
    }

    public void setFundManageInfo(String fundManageInfo) {
        this.fundManageInfo = fundManageInfo;
    }

    public String getFundType() {
        return fundType;
    }

    public void setFundType(String fundType) {
        this.fundType = fundType;
    }

    public BigDecimal getFundQuoteChange() {
        return fundQuoteChange;
    }

    public void setFundQuoteChange(BigDecimal fundQuoteChange) {
        this.fundQuoteChange = fundQuoteChange;
    }

    public BigDecimal getFundQuoteHistChange() {
        return fundQuoteHistChange;
    }

    public void setFundQuoteHistChange(BigDecimal fundQuoteHistChange) {
        this.fundQuoteHistChange = fundQuoteHistChange;
    }

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
