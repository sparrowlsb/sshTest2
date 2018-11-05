package com.world.ico.entity;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by lsb on 2018/9/22.
 */
@Entity
@Table(name="USER_WALLET")
public class UserWalletPo {
    @Id
    @Column(name = "id")
    @GeneratedValue
    Integer id;

    @Column(name = "money")
    BigDecimal money;

    @Column(name = "user_id")
    Integer userId;

    @Column(name = "type")
    String type;

    @Column(name = "count")
    BigDecimal count;


    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
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

    public BigDecimal getCount() {
        return count;
    }

    public void setCount(BigDecimal count) {
        this.count = count;
    }

}
