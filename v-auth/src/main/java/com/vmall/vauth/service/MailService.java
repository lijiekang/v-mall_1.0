package com.vmall.vauth.service;

import com.sun.xml.internal.org.jvnet.mimepull.MIMEMessage;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.concurrent.TimeUnit;

@Component
public class MailService {
    @Resource
    JavaMailSender javaMailSender;
    @Resource
    RedisTemplate redisTemplate;
    public void sendMail(String to,String title,String context,Integer code){
        MimeMessage mimeMessage=javaMailSender.createMimeMessage();
        MimeMessageHelper helper=new MimeMessageHelper(mimeMessage);
        try {
            helper.setFrom("pxz529@yeah.net");
            helper.setTo(to);
            helper.setSubject(title);
            helper.setText(context+code);
            javaMailSender.send(mimeMessage);
            ValueOperations<String,Object> vo=redisTemplate.opsForValue();
            vo.set(to,code);
            redisTemplate.expire(to,15, TimeUnit.MINUTES);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
