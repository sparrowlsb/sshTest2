package com.world.ico.controller;

import com.alibaba.fastjson.JSONObject;
import com.world.ico.dto.EmailAddress;
import com.world.ico.dto.User;
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
    private UserInfoService userInfoService;


    @RequestMapping(value = "getUserInfo", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject getUserInfo( HttpSession session) {
        JSONObject jsonObject=new JSONObject();

        String email= (String) session.getAttribute("email");
        if (email==null){
            return getError(jsonObject,"please login first");
        }
        UserInfo userInfo=userInfoService.getPersonInfo(email);
        if(userInfo==null){
            return getError(jsonObject,"no userID");

        }

        jsonObject.put("userInfo",userInfo);
        return getSuccess(jsonObject,"");

    }

    @RequestMapping(value = "updateInfo", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject setUserInfo(@RequestBody UserInfo userInfo,HttpSession session) {
        JSONObject jsonObject=new JSONObject();
        String email= (String) session.getAttribute("email");
        if (email==null){
            return getError(jsonObject,"please login first");
        }
        UserInfo userInfoOld=userInfoService.getPersonInfo(email);
        if(userInfoOld==null){
            return getError(jsonObject,"no userID");

        }
        if (userInfoOld.getStatus()!=0){
            return getError(jsonObject,"the user is pending check");

        }
        if (userInfo.getIdCardOn()==null){
            return getError(jsonObject,"please put the IdCardOn");
        }
        if (userInfo.getIdCardUnder()==null){
            return getError(jsonObject,"please put the IdCardUnder");
        }
        if (userInfo.getName()==null){
            return getError(jsonObject,"please put the name");
        }
        if (userInfo.getPersonCode()==null){
            return getError(jsonObject,"please put the idCard number");
        }
        if (userInfo.getUsdtAddress()==null){
            return getError(jsonObject,"please put the usdt address");
        }
        userInfoService.updatePersonInfo(email,userInfo);
        return getSuccess(jsonObject,"");

    }


}
