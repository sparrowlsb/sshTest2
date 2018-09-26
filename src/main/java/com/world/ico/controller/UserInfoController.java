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
            System.out.print("!111");

            String email = (String)session.getAttribute("email");
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

            int userId=loginService.findEmailIdByEmail(email);
            if(userId==0){
                return getError(jsonObject,"no userID");

            }

            System.out.print(userInfo.getUserId()+"--"+userId);
            System.out.print(userInfo.getEmail()+"--"+email);
            if(userInfo.getUserId()==userId && userInfo.getEmail().equals(email)){
                if(!userInfo.getBankId().isEmpty()&&!userInfo.getBankName().isEmpty()){
                    userInfoService.updateBankInfo(userInfo.getBankName(),userInfo.getBankId(),userId);
                }
                if(!userInfo.getAlipayId().isEmpty()&&!userInfo.getAllipayName().isEmpty()){
                    userInfoService.updateAlipayInfo(userInfo.getAllipayName(),userInfo.getAlipayId(),userId);

                }
                if(!userInfo.getWechatId().isEmpty()&&!userInfo.getWechatName().isEmpty()){
                    userInfoService.updateWechatInfo(userInfo.getWechatName(),userInfo.getWechatId(),userId);

                }
                return getSuccess(jsonObject,"");
            }

        }catch (Exception e){
            return getError(jsonObject,e.toString());
        }
        return getError(jsonObject,"");

    }
}
