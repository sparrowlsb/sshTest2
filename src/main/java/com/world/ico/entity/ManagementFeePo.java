package com.world.ico.entity;

import javax.persistence.*;
import java.security.Timestamp;

/**
 * Created by lsb on 2018/9/22.
 */

@Entity
@Table(name="MANAGEMENT_FEE")
public class ManagementFeePo {

    @Id
    @Column(name = "id")
    @GeneratedValue
    Integer id;

    @Column(name = "management_fee")
    double managementFee;

    @Column(name = "date")
    Timestamp date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getManagementFee() {
        return managementFee;
    }

    public void setManagementFee(double managementFee) {
        this.managementFee = managementFee;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
}
