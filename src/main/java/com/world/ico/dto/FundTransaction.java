package com.world.ico.dto;

import java.math.BigDecimal;

/**
 * Created by lsb on 2018/10/18.
 */
public class FundTransaction {
    Integer id;

    Integer userId;

    String fundName;

    String type;

    Integer status;

    String transactionStatus;

    BigDecimal traderMoney;

    BigDecimal fundPrice;

    Integer fundId;

    BigDecimal fundCount;

    BigDecimal managementCost;

    String transactionDate;

    String date;

    public String getFundName() {
        return fundName;
    }

    public void setFundName(String fundName) {
        this.fundName = fundName;
    }

    public BigDecimal getFundPrice() {
        return fundPrice;
    }

    public void setFundPrice(BigDecimal fundPrice) {
        this.fundPrice = fundPrice;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public BigDecimal getTraderMoney() {
        return traderMoney;
    }

    public void setTraderMoney(BigDecimal traderMoney) {
        this.traderMoney = traderMoney;
    }

    public Integer getFundId() {
        return fundId;
    }

    public void setFundId(Integer fundId) {
        this.fundId = fundId;
    }

    public BigDecimal getFundCount() {
        return fundCount;
    }

    public void setFundCount(BigDecimal fundCount) {
        this.fundCount = fundCount;
    }

    public BigDecimal getManagementCost() {
        return managementCost;
    }

    public void setManagementCost(BigDecimal managementCost) {
        this.managementCost = managementCost;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }
}
