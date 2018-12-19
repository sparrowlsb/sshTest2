package com.world.ico.util;

import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;


/**
 * Created by lsb on 2018/9/25.
 */

public class CreateSimpleMail {

    private final static String host = "smtp.mxhichina.com"; //163的服务器
    private final static String formName = "noreply@btcome.top";//你的邮箱
    private final static String password = "superman123."; //授权码

    public static void mail(String verifyCode,String emailAddress) throws Exception {
        Properties props = new Properties();

        // 开启debug调试
        props.setProperty("mail.debug", "true");
        // 发送服务器需要身份验证
        props.setProperty("mail.smtp.auth", "true");
        // 设置邮件服务器主机名
        props.setProperty("mail.host", host);
        // 发送邮件协议名称
        props.setProperty("mail.transport.protocol", "smtp");

        //开启了 SSL 加密
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.ssl.socketFactory", sf);

        Session session = Session.getInstance(props);

        Message msg = new MimeMessage(session);
        msg.setSubject("BTCome 验证码");
        StringBuilder builder = new StringBuilder();
        builder.append("亲爱的用户，你好！\n请核对你的邮箱验证码："+verifyCode+"\n如果你未申请我们的服务，请忽略该邮件。\n如果仍有问题，请联系我们的服务专线 \nE-mail: support@btcome.top \n再次感谢你的支持和理解！");
//        builder.append("\n时间 " + new Date());
        msg.setText(builder.toString());
        msg.setFrom(new InternetAddress(formName));//**发送人的邮箱地址**

        Transport transport = session.getTransport();
        transport.connect("smtp.mxhichina.com",formName,password);

        List<String> list=new ArrayList<>();
        //实现群发，下面的方法也是可以实现群发，但是不太理想
        transport.sendMessage(msg, InternetAddress.parse(emailAddress));

        transport.close();

    }
    public static void sendTransactionMail(String mail, String transactionType, BigDecimal transactionCount, String emailAddress) throws Exception {
        Properties props = new Properties();

        // 开启debug调试
        props.setProperty("mail.debug", "true");
        // 发送服务器需要身份验证
        props.setProperty("mail.smtp.auth", "true");
        // 设置邮件服务器主机名
        props.setProperty("mail.host", host);
        // 发送邮件协议名称
        props.setProperty("mail.transport.protocol", "smtp");

        //开启了 SSL 加密
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.ssl.socketFactory", sf);

        Session session = Session.getInstance(props);

        Message msg = new MimeMessage(session);
        String type="";
        if (transactionType.equalsIgnoreCase("BUY")){
            type="充值";
        }else {
            type="提现";
        }
        msg.setSubject("BTCome "+type+"订单");
        StringBuilder builder = new StringBuilder();
        builder.append("订单邮箱是："+mail+"\n交易类型:"+transactionType+"\n交易金额:"+transactionCount+"USDT");
//        builder.append("\n时间 " + new Date());
        msg.setText(builder.toString());
        msg.setFrom(new InternetAddress(formName));//**发送人的邮箱地址**

        Transport transport = session.getTransport();
        transport.connect("smtp.mxhichina.com",formName,password);

        List<String> list=new ArrayList<>();
        //实现群发，下面的方法也是可以实现群发，但是不太理想
        transport.sendMessage(msg, InternetAddress.parse(emailAddress));

        transport.close();

    }
    public static void userConfirmMail(String mail, String transactionType, BigDecimal transactionCount, String emailAddress) throws Exception {
        Properties props = new Properties();

        // 开启debug调试
        props.setProperty("mail.debug", "true");
        // 发送服务器需要身份验证
        props.setProperty("mail.smtp.auth", "true");
        // 设置邮件服务器主机名
        props.setProperty("mail.host", host);
        // 发送邮件协议名称
        props.setProperty("mail.transport.protocol", "smtp");

        //开启了 SSL 加密
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.ssl.socketFactory", sf);

        Session session = Session.getInstance(props);

        Message msg = new MimeMessage(session);
        msg.setSubject("BTCome 验证码");
        StringBuilder builder = new StringBuilder();
        builder.append("订单邮箱是："+mail+"\n交易类型:"+transactionType+"\n交易金额"+transactionCount+"USDT");
//        builder.append("\n时间 " + new Date());
        msg.setText(builder.toString());
        msg.setFrom(new InternetAddress(formName));//**发送人的邮箱地址**

        Transport transport = session.getTransport();
        transport.connect("smtp.mxhichina.com",formName,password);

        List<String> list=new ArrayList<>();
        //实现群发，下面的方法也是可以实现群发，但是不太理想
        transport.sendMessage(msg, InternetAddress.parse(emailAddress));

        transport.close();

    }
    public static void main(String[] args) throws Exception {
        mail("123","a1158362548@qq.com");
    }
}