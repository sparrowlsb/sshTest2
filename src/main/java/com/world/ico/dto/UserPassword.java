package com.world.ico.dto;

/**
 * Created by lsb on 2018/10/16.
 */
public class UserPassword {
    String name;
    String entryPassword;
    String password;
    String passwordFinance;
    String email;
    String verEmailCode;


    public String getName() {
        return name;
    }

    public String getEntryPassword() {
        return entryPassword;
    }

    public void setEntryPassword(String entryPassword) {
        this.entryPassword = entryPassword;
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

    public String getPasswordFinance() {
        return passwordFinance;
    }

    public void setPasswordFinance(String passwordFinance) {
        this.passwordFinance = passwordFinance;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getVerEmailCode() {
        return verEmailCode;
    }

    public void setVerEmailCode(String verEmailCode) {
        this.verEmailCode = verEmailCode;
    }
}
