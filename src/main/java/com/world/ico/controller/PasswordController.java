package com.world.ico.controller;

import com.alibaba.fastjson.JSONObject;
import com.world.ico.dto.UserPassword;
import com.world.ico.service.PasswordService;
import com.world.ico.service.serviceImpl.BaseImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by lsb on 2018/10/16.
 */
@Controller
@RequestMapping("/password")
public class PasswordController extends BaseImpl {
    @Autowired
    private PasswordService passwordService;


    @RequestMapping(value = "passwordForget", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject passwordForget(@RequestBody UserPassword userPassword, HttpSession session) {
        JSONObject jsonObject=new JSONObject();
        String verCode=(String) session.getAttribute("verEmailCode");
        String verEmail=(String) session.getAttribute("verEmail");
        if(verCode.isEmpty()){
            return getError(jsonObject,"please put the eamil address first");
        }
        if(userPassword.getEmail().isEmpty()){
            return getError(jsonObject,"please put the eamil address first");

        }
        if(!verCode.equalsIgnoreCase(userPassword.getVerEmailCode())){
            return getError(jsonObject,"please put the right emailcode");
           
        }
        if(!verEmail.equalsIgnoreCase(userPassword.getEmail())){
            return getError(jsonObject,"please put the right email");

        }
        session.removeAttribute("verEmailCode");
        session.removeAttribute("verEmail");
        passwordService.changePassword(userPassword.getPassword(),userPassword.getEmail());

        return getSuccess(jsonObject,"");
    }

}