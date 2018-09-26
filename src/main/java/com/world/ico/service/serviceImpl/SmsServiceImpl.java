package com.world.ico.service.serviceImpl;

import com.world.ico.service.SmsService;
import com.world.ico.util.CreateSimpleMail;
import com.world.ico.util.VerifyCodeUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by lsb on 2018/9/26.
 */
@Transactional
@Service
public class SmsServiceImpl implements SmsService {
    @Override
    public String sendVerCodeSms(HttpServletResponse response, HttpSession session)  {
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
        try {
            VerifyCodeUtils.outputImage(w, h, response.getOutputStream(), verifyCode);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "Success";
    }

    @Override
    public String sendEmailCodeSms(HttpSession session,String emailAddress)  {
        String verifyCode = VerifyCodeUtils.generateVerifyCode(5);
        try {
            CreateSimpleMail.mail(verifyCode,emailAddress);
        } catch (Exception e) {
            e.printStackTrace();
        }
        session.removeAttribute("verEmailCode");
        session.setAttribute("verEmailCode", verifyCode.toLowerCase());
        return  "Success";
    }
}
