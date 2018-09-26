package com.world.ico.service;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by lsb on 2018/9/19.
 */
public interface SmsService {

    public String sendVerCodeSms(HttpServletResponse response, HttpSession session) throws IOException;

    public String sendEmailCodeSms(HttpSession session,String emailAddress) throws Exception;

}
