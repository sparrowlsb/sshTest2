package com.world.ico.dto;

/**
 * Created by lsb on 2018/10/8.
 */
public class FundPrice {

    Integer fundId;

    Double fundIncrease;

    Double fundPrice;

    Double fundInMoney;

    Double fundOutMoney;

    Double fundTotalMoney;

    String fundDate;

    public Double getFundIncrease() {
        return fundIncrease;
    }

    public void setFundIncrease(Double fundIncrease) {
        this.fundIncrease = fundIncrease;
    }

    public Integer getFundId() {
        return fundId;
    }

    public void setFundId(Integer fundId) {
        this.fundId = fundId;
    }

    public Double getFundPrice() {
        return fundPrice;
    }

    public void setFundPrice(Double fundPrice) {
        this.fundPrice = fundPrice;
    }

    public Double getFundInMoney() {
        return fundInMoney;
    }

    public void setFundInMoney(Double fundInMoney) {
        this.fundInMoney = fundInMoney;
    }

    public Double getFundOutMoney() {
        return fundOutMoney;
    }

    public void setFundOutMoney(Double fundOutMoney) {
        this.fundOutMoney = fundOutMoney;
    }

    public Double getFundTotalMoney() {
        return fundTotalMoney;
    }

    public void setFundTotalMoney(Double fundTotalMoney) {
        this.fundTotalMoney = fundTotalMoney;
    }

    public String getFundDate() {
        return fundDate;
    }

    public void setFundDate(String fundDate) {
        this.fundDate = fundDate;
    }
}
