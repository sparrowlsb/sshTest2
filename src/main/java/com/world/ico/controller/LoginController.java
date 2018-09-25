package com.world.ico.controller;


import com.world.ico.dto.User;
import com.world.ico.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by lsb on 2018/9/7.1
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    private String RULE_EMAIL ="/^[A-Za-z\\d]+([-_.][A-Za-z\\d]+)*@([A-Za-z\\d]+[-.])+[A-Za-z\\d]{2,5}$/";

    @RequestMapping(value = "", method = RequestMethod.GET)

    public String login() {

        return "redirect:pages/login.html";
    }



    @RequestMapping(value = "loginByPassword", produces = "application/json;charset=utf-8" , method = RequestMethod.POST)
    @ResponseBody
    public String loginByPassword(@RequestBody User user) {

        Pattern p = Pattern.compile(RULE_EMAIL);
        Matcher m = p.matcher(user.getEmail());

        if(user.getEmail().isEmpty()&&user.getPassword().isEmpty()){
            return "enter email or password is empty";
        }
        if(!m.matches()){
            return "error email";
        }
        Integer count=loginService.findUser(user.getEmail(),user.getPassword());
        if(count==1){
            return "success";
        }
        return "error";

    }

    @RequestMapping(value = "existenceUser", produces = "application/json;charset=utf-8" , method = RequestMethod.POST)
    @ResponseBody
    public String existenceUser(@RequestBody User user) {


        Pattern p = Pattern.compile(RULE_EMAIL);
        Matcher m = p.matcher(user.getEmail());

        if(user.getEmail().isEmpty()){
            return "email is empty";
        }
        if(!m.matches()){
            return "error email";
        }
        Integer counByEmail=loginService.findEmailIdByEmail(user.getEmail());
        if(counByEmail!=0){
            return "emailExistence";
        }

        return "success";

    }

    @RequestMapping(value = "registerUser", produces = "application/json;charset=utf-8" , method = RequestMethod.POST)
    @ResponseBody
    public String registerUser(@RequestBody User user) {
        Pattern p = Pattern.compile(RULE_EMAIL);
        Matcher m = p.matcher(user.getEmail());
        if(user.getEmail().isEmpty()&&user.getPassword().isEmpty()&&user.getName().isEmpty()){
            return "enter email or password is empty";
        }
        if(!m.matches()){
            return "error email";
        }
        Integer counByEmail=loginService.findEmailIdByEmail(user.getEmail());
        if(counByEmail!=0){
            return "emailExistence";
        }


        loginService.addUser(user.getEmail(),user.getName(),user.getPassword());
        Integer emailId=loginService.findEmailIdByEmail(user.getEmail());
        loginService.addUserInfo(emailId);
        loginService.addUserWallet(emailId);

        return "success";

    }
}
