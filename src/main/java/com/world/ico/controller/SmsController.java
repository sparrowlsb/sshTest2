package com.world.ico.controller;

import com.world.ico.dto.SmsInfo;
import com.world.ico.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by lsb on 2018/9/19.
 */
@Controller
@RequestMapping("/")
public class SmsController {

    @Autowired
    private SmsService smsService;

    @RequestMapping(value = "sendRegisterSMS", produces = "application/json;charset=utf-8" , method = RequestMethod.POST)
    @ResponseBody
    public String sendSms(SmsInfo smsInfo) {

        String result = "";

        try {
            smsService.sendSms(smsInfo);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
