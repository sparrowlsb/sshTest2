package com.world.ico.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by lsb on 2018/10/18.
 */
public class FundTransaction {
    Integer id;

    Integer userId;

    String type;

    Integer status;

    BigDecimal traderMoney;

    BigDecimal fundPrice;

    Integer fundId;

    BigDecimal fundCount;

    BigDecimal managementCost;

    Timestamp transactionDate;

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

    public Timestamp getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Timestamp transactionDate) {
        this.transactionDate = transactionDate;
    }
}
