package com.world.ico.controller;

import com.alibaba.fastjson.JSONObject;
import com.world.ico.dto.UserInfo;
import com.world.ico.service.LoginService;
import com.world.ico.service.UserInfoService;
import com.world.ico.service.serviceImpl.BaseImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by lsb on 2018/9/26.
 */

@Controller
@RequestMapping("/userInfo")
public class UserInfoController extends BaseImpl{
    @Autowired
    private LoginService loginService;
    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping(value = "updateInfo", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject updateInfo(@RequestBody UserInfo userInfo, HttpSession session) {
        JSONObject jsonObject=new JSONObject();
        try {

            String email = (String)session.getAttribute("email");
            String userInfoEmail = (String)session.getAttribute("userInfoEmail");
            if(email.isEmpty()){
                return getError(jsonObject,"no email please login first");

            }
            String userInfoCode = (String)session.getAttribute("userInfoCode");

            if(userInfoCode.isEmpty()){
                return getError(jsonObject,"the code is empty please get the userinfocode first");
            }

            if(!userInfoCode.equalsIgnoreCase(userInfo.getUserInfoCode())){
                return getError(jsonObject,"the code is error");
            }
            if(!userInfoEmail.equalsIgnoreCase(email)){
                return getError(jsonObject,"the email is error");
            }

            int userId=loginService.findEmailIdByEmail(email);
            if(userId==0){
                return getError(jsonObject,"no userID");

            }
            session.removeAttribute("userInfoEmail");

                return getSuccess(jsonObject,"");


        }catch (Exception e){
            return getError(jsonObject,e.toString());
        }

    }
    @RequestMapping(value = "setPersonCode", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject setPersonCode(@RequestBody UserInfo userInfo, HttpSession session) {
        JSONObject jsonObject=new JSONObject();
        try {

            String email = (String)session.getAttribute("email");
            String personCode=userInfo.getPersonCode();
            if(email.isEmpty()){
                return getError(jsonObject,"no email please login first");

            }
            if(!userInfoService.getPersonCode(email).isEmpty()){
                return getError(jsonObject,"have the personCode");
            }

            if(personCode.isEmpty()){
                return getError(jsonObject,"please set the personcode");
            }
            userInfoService.setPersonCode(personCode,email);


        }catch (Exception e){
            return getError(jsonObject,e.toString());
        }
        return getError(jsonObject,"");

    }
}
