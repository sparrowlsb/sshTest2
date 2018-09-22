package com.world.ico.entity;

import javax.persistence.*;
import java.security.Timestamp;

/**
 * Created by lsb on 2018/9/19.
 */

@Entity
@Table(name="USER_VERCODE")
public class UserVercodePo {
    @Id
    @Column(name = "id")
    @GeneratedValue
    Integer id;

    @Column(name = "user_id")
    Integer userId;

    @Column(name = "type")
    String type;

    @Column(name = "vercode")
    Integer vercode;

    @Column(name = "send_date")
    Timestamp sendDate;

    @Column(name = "status")
    Integer status;

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

    public Integer getVercode() {
        return vercode;
    }

    public void setVercode(Integer vercode) {
        this.vercode = vercode;
    }

    public Timestamp getSendDate() {
        return sendDate;
    }

    public void setSendDate(Timestamp sendDate) {
        this.sendDate = sendDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
