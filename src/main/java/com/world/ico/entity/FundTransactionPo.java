package com.world.ico.entity;

import javax.persistence.*;
import java.security.Timestamp;

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

    @Column(name = "fund_id")
    Integer fundId;

    @Column(name = "type")
    String type;

    @Column(name = "fund_price")
    Double fundPrice;

    @Column(name = "fund_count")
    Double fundCount;

    @Column(name = "management_fee")
    Double managementFee;

    @Column(name = "management_cost")
    Double managementCost;

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

    public Integer getFundId() {
        return fundId;
    }

    public void setFundId(Integer fundId) {
        this.fundId = fundId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getFundPrice() {
        return fundPrice;
    }

    public void setFundPrice(Double fundPrice) {
        this.fundPrice = fundPrice;
    }

    public Double getFundCount() {
        return fundCount;
    }

    public void setFundCount(Double fundCount) {
        this.fundCount = fundCount;
    }

    public Double getManagementFee() {
        return managementFee;
    }

    public void setManagementFee(Double managementFee) {
        this.managementFee = managementFee;
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
