package com.world.ico.dto;

/**
 * Created by lsb on 2017/3/18.
 */
public class News {

    private int id;

    private int level;

    private String[]  riskType;

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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String[] getRiskType() {
        return riskType;
    }

    public void setRiskType(String[] riskType) {
        this.riskType = riskType;
    }
}
