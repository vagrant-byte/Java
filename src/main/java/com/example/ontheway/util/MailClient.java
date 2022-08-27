package com.example.ontheway.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
public class MailClient {
//    设置日志
    private static final Logger logger= LoggerFactory.getLogger(MailClient.class);

//    注入发邮件的方法
    @Autowired
    private JavaMailSender mailSender;

//    发送方用户名
    @Value("${spring.mail.username}")
    private String from;

    public void sendMail(String to,String subject,String content) {
        //发邮件的方法，to:收件人 subject:主题 content:内容
        try {
            MimeMessage mimeMessage=mailSender.createMimeMessage();
            MimeMessageHelper helper=new MimeMessageHelper(mimeMessage);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
//            发送内容以html解析
            helper.setText(content,true);
//            发送邮件
            mailSender.send(helper.getMimeMessage());
        } catch (MessagingException e) {
            logger.error("发送邮件失败:"+e.getMessage());
        }
    }
}
