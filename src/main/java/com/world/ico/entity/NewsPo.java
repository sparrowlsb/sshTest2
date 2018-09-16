package com.world.ico.entity;

import javax.persistence.*;

/**
 * Created by lsb on 2017/3/18.
 */
@Entity
@Table(name="t_crawler_content")
public class NewsPo {
    @Id
    @Column(name = "id")
    @GeneratedValue
    private int id;

    @Column(name = "LEVEL")

    private int level;

    @Column(name = "riskType")

    private String  riskType;

    @Column(name = "CREATE_TIME")

    private String createTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getRiskType() {
        return riskType;
    }

    public void setRiskType(String riskType) {
        this.riskType = riskType;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
