package com.world.ico.dto;

/**
 * Created by lsb on 2018/9/8.
 */

public class User {

    String name;
    String password;
    String email;
    String verCode;
    String verEmailCode;
    String usdtAddress;
    String personCode;

    public String getUsdtAddress() {
        return usdtAddress;
    }

    public void setUsdtAddress(String usdtAddress) {
        this.usdtAddress = usdtAddress;
    }

    public String getPersonCode() {
        return personCode;
    }

    public void setPersonCode(String personCode) {
        this.personCode = personCode;
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

    public String getVerCode() {
        return verCode;
    }

    public void setVerCode(String verCode) {
        this.verCode = verCode;
    }

    public String getVerEmailCode() {
        return verEmailCode;
    }

    public void setVerEmailCode(String verEmailCode) {
        this.verEmailCode = verEmailCode;
    }
}
