package com.world.ico.entity;

import javax.persistence.*;

/**
 * Created by lsb on 2018/9/22.
 */
@Entity
@Table(name="FUND_MANAGE")
public class FundManagePo {
    @Id
    @Column(name = "manage_id")
    @GeneratedValue
    Integer manageId;

    @Column(name = "manage_name")
    String manageName;

    @Column(name = "manage_sex")
    String manageSex;

    @Column(name = "manage_age")
    Integer manageAge;

    @Column(name = "manage_info")
    String manageInfo;

    @Column(name = "manage_speciality")
    String manageSpeciality;

    public Integer getManageId() {
        return manageId;
    }

    public void setManageId(Integer manageId) {
        this.manageId = manageId;
    }

    public String getManageName() {
        return manageName;
    }

    public void setManageName(String manageName) {
        this.manageName = manageName;
    }

    public String getManageSex() {
        return manageSex;
    }

    public void setManageSex(String manageSex) {
        this.manageSex = manageSex;
    }

    public Integer getManageAge() {
        return manageAge;
    }

    public void setManageAge(Integer manageAge) {
        this.manageAge = manageAge;
    }

    public String getManageInfo() {
        return manageInfo;
    }

    public void setManageInfo(String manageInfo) {
        this.manageInfo = manageInfo;
    }

    public String getManageSpeciality() {
        return manageSpeciality;
    }

    public void setManageSpeciality(String manageSpeciality) {
        this.manageSpeciality = manageSpeciality;
    }
}
