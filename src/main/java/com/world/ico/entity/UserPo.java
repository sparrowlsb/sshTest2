package com.world.ico.entity;

import javax.persistence.*;

/**
 * Created by lsb on 2018/9/8.
 */
@Entity
@Table(name="USER")
public class UserPo {
    @Id
    @Column(name = "id")
    @GeneratedValue
    Integer id;

    @Column(name = "email")
    String email;

    @Column(name = "user_name")
    String user;

    @Column(name = "user_password")
    String password;

    public Integer getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }




}
