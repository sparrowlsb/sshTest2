package com.world.ico.util;

import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;


/**
 * Created by lsb on 2018/9/25.
 */

public class CreateSimpleMail {
    public static void mail(String verifyCode,String emailAddress) throws Exception {
        Properties props = new Properties();

        // 开启debug调试
        props.setProperty("mail.debug", "true");
        // 发送服务器需要身份验证
        props.setProperty("mail.smtp.auth", "true");
        // 设置邮件服务器主机名
        props.setProperty("mail.host", "smtp.mxhichina.com");
        // 发送邮件协议名称
        props.setProperty("mail.transport.protocol", "smtp");

        //开启了 SSL 加密
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.ssl.socketFactory", sf);

        Session session = Session.getInstance(props);

        Message msg = new MimeMessage(session);
        msg.setSubject("众庄交易大师平台 验证码");
        StringBuilder builder = new StringBuilder();
        builder.append("注册验证码是："+verifyCode);
        builder.append("\n时间 " + new Date());
        msg.setText(builder.toString());
        msg.setFrom(new InternetAddress("noreply@zhongzhuang.fund"));//**发送人的邮箱地址**

        Transport transport = session.getTransport();
        transport.connect("smtp.mxhichina.com","noreply@zhongzhuang.fund","superman.123");

        List<String> list=new ArrayList<>();
        //实现群发，下面的方法也是可以实现群发，但是不太理想
        transport.sendMessage(msg, InternetAddress.parse(emailAddress));

        transport.close();

    }
    public static void main(String[] args) throws Exception {
        mail("123","a1158362548@qq.com");
    }
}