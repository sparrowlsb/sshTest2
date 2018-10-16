package com.world.ico.controller;


import com.alibaba.fastjson.JSONObject;
import com.world.ico.dto.User;
import com.world.ico.service.LoginService;
import com.world.ico.service.serviceImpl.BaseImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by lsb on 2018/9/7.1
 */
@Controller
@RequestMapping("/login")
public class LoginController extends BaseImpl{

    @Autowired
    private LoginService loginService;

    private final String RULE_EMAIL ="^[A-Za-z\\d]+([-_.][A-Za-z\\d]+)*@([A-Za-z\\d]+[-.])+[A-Za-z\\d]{2,5}$";

    @RequestMapping(value = "", method = RequestMethod.GET)

    public String login() {

        return "redirect:pages/login.html";
    }



    @RequestMapping(value = "loginByPassword", produces = "application/json;charset=utf-8" , method = RequestMethod.POST)
    @ResponseBody
    public JSONObject loginByPassword(@RequestBody User user,HttpSession session) {


        String verCode=(String) session.getAttribute("verCode");
        session.removeAttribute("verCode");
        JSONObject jsonObject=new JSONObject();
        System.out.println(verCode);
        if(verCode.isEmpty()){
            return getError(jsonObject,"the vercode not get");
        }
        if(!user.getVerCode().equalsIgnoreCase(verCode)){

            return getError(jsonObject,"the vercode not right");
        }
        Pattern p = Pattern.compile(RULE_EMAIL);
        Matcher m = p.matcher(user.getEmail());

        if(user.getEmail().isEmpty()&&user.getPassword().isEmpty()){
            return getError(jsonObject,"enter email or password is empty");
        }
        if(!m.matches()){
            return getError(jsonObject,"error email");

        }
        Integer count=loginService.findUser(user.getEmail(),user.getPassword());
        if(count==1){
            session.setAttribute("email",user.getEmail());
            return getSuccess(jsonObject,"");

        }
        return getError(jsonObject,"error");


    }

    @RequestMapping(value = "existenceUser", produces = "application/json;charset=utf-8" , method = RequestMethod.POST)
    @ResponseBody
    public JSONObject existenceUser(@RequestBody User user) {
        JSONObject jsonObject=new JSONObject();

        Pattern p = Pattern.compile(RULE_EMAIL);
        Matcher m = p.matcher(user.getEmail());

        if(user.getEmail().isEmpty()){
            return getError(jsonObject,"email is empty");
        }
        if(!m.matches()){
            return getError(jsonObject,"error email");
        }
        Integer counByEmail=loginService.findEmailIdByEmail(user.getEmail());
        if(counByEmail!=0){
            return getError(jsonObject,"emailExistence");
        }

        return getSuccess(jsonObject,"");

    }

    @RequestMapping(value = "registerUser", produces = "application/json;charset=utf-8" , method = RequestMethod.POST)
    @ResponseBody
    public JSONObject registerUser(@RequestBody User user,HttpSession session) {
        JSONObject jsonObject=new JSONObject();
        String verCode=(String) session.getAttribute("verCode");
        String verEamilCode=(String) session.getAttribute("verEmailCode");
        String verEamil=(String) session.getAttribute("verEmail");
        session.removeAttribute("verCode");
        if(verCode.isEmpty()){
            return getError(jsonObject,"the vercode not get");
        }
        if(verEamilCode.isEmpty()){
            return getError(jsonObject,"the verEmailCode not get");
        }
        if(!user.getVerCode().equalsIgnoreCase(verCode)){

            return getError(jsonObject,"the verCode not right");
        }
        if(!user.getVerEmailCode().equalsIgnoreCase(verEamilCode)){

            return getError(jsonObject,"the verEmailCode not right");
        }
        if(!user.getEmail().equalsIgnoreCase(verEamil)){

            return getError(jsonObject,"the verEmail not right");
        }
        session.removeAttribute("verEmail");
        Pattern p = Pattern.compile(RULE_EMAIL);
        Matcher m = p.matcher(user.getEmail());
        if(user.getEmail().isEmpty()&&user.getPassword().isEmpty()&&user.getName().isEmpty()){
            return getError(jsonObject,"enter email or password is empty");
        }
        if(!m.matches()){
            return getError(jsonObject,"error email");
        }
        Integer countByEmail=loginService.findEmailIdByEmail(user.getEmail());
        if(countByEmail!=0){
            return getError(jsonObject,"emailExistence");
        }

        synchronized (this) {
            loginService.addUser(user.getEmail(),user.getPassword());
            Integer emailId = loginService.findEmailIdByEmail(user.getEmail());
            loginService.addUserInfo(emailId);
            loginService.addUserWallet(emailId);
            session.setAttribute("email", user.getEmail());
        }

            return getSuccess(jsonObject, "");

    }
}
