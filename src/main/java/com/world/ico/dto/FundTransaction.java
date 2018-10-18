package com.world.ico.dto;

import java.security.Timestamp;

/**
 * Created by lsb on 2018/10/18.
 */
public class FundTransaction {
    Integer id;
    Integer userId;
    String type;
    Integer status;
    Double traderMoney;
    Integer fundId;
    Double fundCount;
    Double managementCost;
    Timestamp transactionDate;

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

    public Double getTraderMoney() {
        return traderMoney;
    }

    public void setTraderMoney(Double traderMoney) {
        this.traderMoney = traderMoney;
    }

    public Integer getFundId() {
        return fundId;
    }

    public void setFundId(Integer fundId) {
        this.fundId = fundId;
    }

    public Double getFundCount() {
        return fundCount;
    }

    public void setFundCount(Double fundCount) {
        this.fundCount = fundCount;
    }

    public Double getManagementCost() {
        return managementCost;
    }

    public void setManagementCost(Double managementCost) {
        this.managementCost = managementCost;
    }

    public Timestamp getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Timestamp transactionDate) {
        this.transactionDate = transactionDate;
    }
}
