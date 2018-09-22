package com.world.ico.entity;

import javax.persistence.*;

/**
 * Created by lsb on 2018/9/8.
 */
@Entity
@Table(name="USER")
public class UserPo {
    @Id
    @Column(name = "user_id")
    @GeneratedValue
    Integer user_id;

    @Column(name = "email")
    String email;

    @Column(name = "name")
    String name;

    @Column(name = "password")
    String password;

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
