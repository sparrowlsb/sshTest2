package com.world.ico.controller;


import com.world.ico.dto.User;
import com.world.ico.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by lsb on 2018/9/7.
 */
@Controller
@RequestMapping("/")
public class LoginController {

    @Autowired
    private LoginService loginService;
    @RequestMapping(value = "index", method = RequestMethod.GET)

    public String login() {

        return "redirect:pages/index.html";
    }



    @RequestMapping(value = "loginByPassword", produces = "application/json;charset=utf-8" , method = RequestMethod.POST)
    @ResponseBody
    public String loginByPassword(@RequestBody User user) {


        Integer count=loginService.findUser(user.getUser(),user.getPassword());
        if(count==1){
            return "success";
        }
        Integer count2=loginService.findEmail(user.getUser(),user.getPassword());
        if(count2==1){
            return "success";
        }

        return "error";

    }

    @RequestMapping(value = "registerUser", produces = "application/json;charset=utf-8" , method = RequestMethod.POST)
    @ResponseBody
    public String registerUser(@RequestBody User user) {

        Integer counByEmail=loginService.findUserByEmail(user.getEmail());
        if(counByEmail!=0){
            return "emailerror";
        }
        Integer counByUser=loginService.findUserByUsername(user.getUser());
        if(counByUser!=0){
            return "usernameerror";
        }
        loginService.addUser(user.getEmail(),user.getUser(),user.getPassword());

        return "success";

    }
}
