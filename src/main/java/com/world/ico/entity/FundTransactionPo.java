package com.world.ico.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by lsb on 2018/9/22.
 */
@Entity
@Table(name="FUND_TRANSACTION")
public class FundTransactionPo {

    @Id
    @Column(name = "id")
    @GeneratedValue
    Integer id;

    @Column(name = "user_id")
    Integer userId;

    @Column(name = "type")
    String type;

    @Column(name = "status")
    Integer status;

    @Column(name = "trader_money")
    BigDecimal traderMoney;

    @Column(name = "fund_id")
    Integer fundId;

    @Column(name = "fund_price")
    BigDecimal fundPrice;

    @Column(name = "fund_count")
    BigDecimal fundCount;

    @Column(name = "management_fee")
    BigDecimal managementFee;

    @Column(name = "management_cost")
    BigDecimal managementCost;

    @Column(name = "transaction_date")
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

    public BigDecimal getFundPrice() {
        return fundPrice;
    }

    public void setFundPrice(BigDecimal fundPrice) {
        this.fundPrice = fundPrice;
    }

    public BigDecimal getFundCount() {
        return fundCount;
    }

    public void setFundCount(BigDecimal fundCount) {
        this.fundCount = fundCount;
    }

    public BigDecimal getManagementFee() {
        return managementFee;
    }

    public void setManagementFee(BigDecimal managementFee) {
        this.managementFee = managementFee;
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
