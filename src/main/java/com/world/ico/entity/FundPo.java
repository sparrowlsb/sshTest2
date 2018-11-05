package com.world.ico.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by lsb on 2018/9/22.
 */
@Entity
@Table(name="FUND")
public class FundPo {

    @Id
    @Column(name = "fund_id")
    @GeneratedValue
    Integer fundId;

    @Column(name = "fund_name")
    String fundName;

    @Column(name = "fund_manage_id")
    Integer fundManageId;

    @Column(name = "fund_type")
    String fundType;

    @Column(name = "fund_price")
    BigDecimal fundPrice;

    @Column(name = "fund_start_date")
    Timestamp fundStartDate;

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

    public Integer getFundManageId() {
        return fundManageId;
    }

    public void setFundManageId(Integer fundManageId) {
        this.fundManageId = fundManageId;
    }

    public String getFundType() {
        return fundType;
    }

    public void setFundType(String fundType) {
        this.fundType = fundType;
    }

    public BigDecimal getFundPrice() {
        return fundPrice;
    }

    public void setFundPrice(BigDecimal fundPrice) {
        this.fundPrice = fundPrice;
    }

    public Timestamp getFundStartDate() {
        return fundStartDate;
    }

    public void setFundStartDate(Timestamp fundStartDate) {
        this.fundStartDate = fundStartDate;
    }
}
