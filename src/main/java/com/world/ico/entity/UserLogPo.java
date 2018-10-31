package com.world.ico.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by lsb on 2018/9/22.
 */
@Entity
@Table(name="USER_LOG")
public class UserLogPo {
    @Id
    @Column(name = "id")
    @GeneratedValue
    Integer id;

    @Column(name = "user_id")
    Integer userId;

    @Column(name = "log_date")
    Timestamp logDate;

    @Column(name = "active")
    Timestamp active;

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

    public Timestamp getLogDate() {
        return logDate;
    }

    public void setLogDate(Timestamp logDate) {
        this.logDate = logDate;
    }

    public Timestamp getActive() {
        return active;
    }

    public void setActive(Timestamp active) {
        this.active = active;
    }
}
