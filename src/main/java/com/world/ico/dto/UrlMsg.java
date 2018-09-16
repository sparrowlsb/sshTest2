package com.world.ico.dto;

import java.io.Serializable;
//评论
/**
 * Created by youmingwei on 16/4/26.
 */
public class UrlMsg implements Serializable {
    //0:client register;   1:server listUrl send;  2:client news url send;
    private int type;
    private String clientHost;
    private String clientPID;
    private ListUrlUnit listUrlUnit;
    private int solveResultType; //0-错误, 1-正确


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getClientHost() {
        return clientHost;
    }

    public void setClientHost(String clientHost) {
        this.clientHost = clientHost;
    }

    public String getClientPID() {
        return clientPID;
    }

    public void setClientPID(String clientPID) {
        this.clientPID = clientPID;
    }

    public ListUrlUnit getListUrlUnit() {
        return listUrlUnit;
    }

    public void setListUrlUnit(ListUrlUnit listUrlUnit) {
        this.listUrlUnit = listUrlUnit;
    }

    public int getSolveResultType() {
        return solveResultType;
    }

    public void setSolveResultType(int solveResultType) {
        this.solveResultType = solveResultType;
    }
}
