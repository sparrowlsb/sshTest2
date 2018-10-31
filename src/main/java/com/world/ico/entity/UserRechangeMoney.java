package com.world.ico.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by lsb on 2018/9/22.
 */
@Entity
@Table(name="USER_RECHARGE_MONEY")
public class UserRechangeMoney {

    @Id
    @Column(name = "id")
    @GeneratedValue
    Integer id;

    @Column(name = "user_id")
    Integer userId;

    @Column(name = "recharge_type")
    String rechargeType;

    @Column(name = "recharge_platform")
    Integer rechargePlatform;

    @Column(name = "recharge_date")
    Timestamp rechargeDate;

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

    public String getRechargeType() {
        return rechargeType;
    }

    public void setRechargeType(String rechargeType) {
        this.rechargeType = rechargeType;
    }

    public Integer getRechargePlatform() {
        return rechargePlatform;
    }

    public void setRechargePlatform(Integer rechargePlatform) {
        this.rechargePlatform = rechargePlatform;
    }

    public Timestamp getRechargeDate() {
        return rechargeDate;
    }

    public void setRechargeDate(Timestamp rechargeDate) {
        this.rechargeDate = rechargeDate;
    }
}
