package com.world.ico.controller;

import com.world.ico.service.SmsService;
import com.world.ico.util.VerifyCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * Created by lsb on 2018/9/19.
 */
@Controller
@RequestMapping("/sms")

public class SmsCodeServlet {

    @Autowired
    private SmsService smsService;

    private ByteArrayInputStream inputStream;


    @RequestMapping(value = "/VerifyCode", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    @ResponseBody
    public void VerifyCode(HttpServletRequest request, HttpServletResponse response,HttpSession session) throws IOException {
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setContentType("image/jpeg");

            //生成随机字串
            String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
            //存入会话session

            //删除以前的
            session.removeAttribute("verCode");
            session.setAttribute("verCode", verifyCode.toLowerCase());
            //生成图片
            int w = 100, h = 30;
            VerifyCodeUtils.outputImage(w, h, response.getOutputStream(), verifyCode);

        }

    }